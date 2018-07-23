package com.knoldus

import org.scalatest.FunSuite

class UrlParsingSpec extends FunSuite {

  val testParsing = new UrlParsing

  test("URL parsing for valid input"){
    val actualResult =testParsing.extractingDetails("https://aws.amazon.com/console/home?state=hash&isauthcode=true&code=112")
    val expectedResult ="protocol =https\n domain =aws.amazon.com\n path =console/home\n params =Map(state -> hash, isauthcode -> true, code -> 112)"

    assert(actualResult === expectedResult)
  }

  test("URL parsing for invalid input"){
    val actualResult =testParsing.extractingDetails("https:hello.com")
    val expectedResult= "Not a valid URL"

    assert(actualResult === expectedResult)

      }

}
