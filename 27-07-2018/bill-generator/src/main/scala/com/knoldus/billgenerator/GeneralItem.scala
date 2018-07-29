
package com.knoldus.billgenerator

class GeneralItem extends Item {

  val VAT = 0.025

  override def getBill(mapOfitems: Map[String, Int], priceOfItems: Map[String, Double]): Double = {


    val BillForGeneralItem = mapOfitems.map { ele =>
      priceOfItems.map { result => (result._2 * ele._2) + (result._2 * ele._2 * VAT)
      }

    }
    val totalBillForGeneralItem = BillForGeneralItem.toList.flatten.sum
    totalBillForGeneralItem
  }
}
