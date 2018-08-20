
package com.knoldus.billgenerator



import org.scalatest.FunSuite

class GeneralItemSpec extends FunSuite {

  val testGeneralItem = new GeneralItem

  test("generate bill for general item") {
    val actualResult = testGeneralItem.getBill(Map("apple" -> 2, "surfexcel" -> 1, "chocolate" -> 5), Map("apple" -> 200, "surfexcel" -> 58, "chocolate" -> 25))
    val expectedResult = 597.575
    assert(actualResult === expectedResult)
  }
}
