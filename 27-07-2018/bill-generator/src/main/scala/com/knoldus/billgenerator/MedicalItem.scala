
package com.knoldus.billgenerator

class MedicalItem extends Item {

  override def getBill(mapOfitems: Map[String, Int], priceOfItems: Map[String, Double]): Double = {


    val totalBillForMedicalItem= for(
      item1<-mapOfitems;
      item2<-priceOfItems
      if(item1._1==item2._1)
    )yield item1._2 * item2._2

    /*val BillForMedicalItem = mapOfitems.map { ele =>
      priceOfItems.map { result => result._2 * ele._2
      }

    }

    val totalBillForMedicalItem = BillForMedicalItem.toList.flatten.sum
    totalBillForMedicalItem*/
    totalBillForMedicalItem.toList.sum
  }
}

