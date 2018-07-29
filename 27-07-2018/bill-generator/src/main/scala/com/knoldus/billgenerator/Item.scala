
package com.knoldus.billgenerator

trait Item {

  def getBill(mapOfitems: Map[String, Int], priceOfItems: Map[String, Double]): Double

}


