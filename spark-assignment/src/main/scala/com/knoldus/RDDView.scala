
package com.knoldus

import org.apache.spark.{SparkConf, SparkContext}

object RDDView extends App {

  val conf = new SparkConf().setMaster("local").setAppName("spark demo")
  val sc = new SparkContext(conf)

  val rdd = sc.parallelize(Array((1, Array((3, 4), (4, 5))), (2, Array((4, 2), (4, 4), (3, 9)))))

  rdd.flatMapValues(values => values).collect.foreach(result => println(result))

}
