
package com.knoldus.bill

import com.knoldus.billgenerator.{GeneralItem, MedicalItem}

class Bill {

  def displayBill(medicalItem: MedicalItem, generalItem: GeneralItem): Any = {

    val totalMedicalBill = medicalItem.getBill(Map("disprin" -> 2, "D-cold total" -> 3, "crocin" -> 5),
      Map("disprin" -> 10, "D-cold total" -> 15, "crocin" -> 5))
    val totalGeneralBill = generalItem.getBill(Map("apple" -> 2, "surfexcel" -> 1, "chocolate" -> 5),Map("apple" -> 200, "surfexcel" -> 58, "chocolate" -> 25))
    val totalBill = totalGeneralBill + totalMedicalBill
    totalBill

  }


}

/*
object Bill1 extends App{
  val bill=new Bill

  val medicalItem = new MedicalItem
  val generalItem=new GeneralItem
  print(bill.displayBill(medicalItem,generalItem))
}
*/
