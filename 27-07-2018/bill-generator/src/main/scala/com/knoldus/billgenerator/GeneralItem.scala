
package com.knoldus.billgenerator

class GeneralItem extends Item {

  val VAT = 0.025

  override def getBill(mapOfitems: Map[String, Int], priceOfItems: Map[String, Double]): Double = {


   val totalBillForGeneralItem= for(
      item1<-mapOfitems;
      item2<-priceOfItems
      if(item1._1==item2._1)
    )yield item1._2 * item2._2*1.025
   /* val BillForGeneralItem = mapOfitems.map { ele =>
      priceOfItems.map { result => (result._2 * ele._2) + (result._2 * ele._2 * VAT)
      }

    }*/
    //println(BillForGeneralItem)
    //val totalBillForGeneralItem = BillForGeneralItem.toList.flatten
    //
    totalBillForGeneralItem.toList.sum
  }
}


/*object x extends App {
  val o = new GeneralItem
  val z = o.getBill(Map("apple" -> 2, "surfexcel" -> 1, "chocolate" -> 5), Map("apple" -> 200, "surfexcel" -> 58, "chocolate" -> 25))
  print(z)
}*/



