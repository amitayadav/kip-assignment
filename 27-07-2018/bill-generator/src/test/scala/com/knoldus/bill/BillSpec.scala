
package com.knoldus.bill

import com.knoldus.billgenerator.{GeneralItem, MedicalItem}
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito.when

class BillSpec extends FunSuite with MockitoSugar{

  val mockGeneralItem = mock[GeneralItem]

  val mockMedicalItem = mock[MedicalItem]
  val testBill:Bill= new Bill

  test("display bill"){
    when(mockMedicalItem.getBill(Map("disprin"->2,"D-cold total"->3,"crocin"->5),Map("disprin"->10,"D-cold total"->15,"crocin"->5))).thenReturn(90)
    when(mockGeneralItem.getBill(Map("apple"->2,"surfexcel"->1,"chocolate"->5),Map("apple"->200,"surfexcel"->58,"chocolate"->25))).thenReturn(579.575)
    val actualResult=testBill.displayBill(mockMedicalItem,mockGeneralItem)
    val expectedResult=669.575
    assert(actualResult===expectedResult)
  }
}
