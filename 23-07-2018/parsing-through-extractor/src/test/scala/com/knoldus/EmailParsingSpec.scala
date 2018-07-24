package com.knoldus

import org.scalatest.FunSuite


class EmailParsingSpec extends FunSuite {

  val testParsing = new EmailParsing

  test("E-Mail parsing for valid input") {
    val actualResult = testParsing.emailParsing("knol@knoldus.in")
    val expectedResult = Some("E-mail =knol@knoldus.in\nuser =knol\ndomain =knoldus.in")
    assert(actualResult === expectedResult)
  }

  test("E-Mail parsing for invalid input") {
    val actualResult = testParsing.emailParsing("https:hello.com")
    val expectedResult = Some("did not match with regex")

    assert(actualResult === expectedResult)

  }

}
