
package com.knoldus.billgenerator

class MedicalItem extends Item {

  override def getBill(mapOfitems: Map[String, Int], priceOfItems: Map[String, Double]): Double = {


    val BillForMedicalItem = mapOfitems.map { ele =>
      priceOfItems.map { result => result._2 * ele._2
      }

    }

    val totalBillForMedicalItem = BillForMedicalItem.toList.flatten.sum
    totalBillForMedicalItem
  }
}

