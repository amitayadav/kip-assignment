
package com.knoldus.billgenerator

import org.scalatest.FunSuite

class MedicalItemSpec extends FunSuite {
  val testMedicalItem = new MedicalItem

  test("generate bill for medical item") {
    val actualResult = testMedicalItem.getBill(Map("disprin" -> 2, "D-cold total" -> 3, "crocin" -> 5), Map("disprin" -> 10, "D-cold total" -> 15, "crocin" -> 5))
    val expectedResult = 90
    assert(actualResult === expectedResult)
  }

}
