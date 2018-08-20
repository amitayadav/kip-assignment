package com.knoldus.billgenerator

class Beef extends Item {
  val VAT = 0.30


  override def getBill(mapOfitems: Map[String, Int], priceOfItems: Map[String, Double]): Double = {
    val totalBillForBeefItem= for {
      item1 <- mapOfitems
      item2 <- priceOfItems if item1._1 == item2._1
    } yield (item1._2 * item2._2) + (item1._2 * item2._2 * VAT)

    totalBillForBeefItem.toList.sum
  }

}
