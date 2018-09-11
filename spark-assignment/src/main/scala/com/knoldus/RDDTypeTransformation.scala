package com.knoldus

import org.apache.spark.{SparkConf, SparkContext}

object RDDTypeTransformation extends App {

  val conf = new SparkConf().setMaster("local").setAppName("spark demo")
  val sc = new SparkContext(conf)

  val rdd = sc.parallelize(Array((1, List(1, 2, 3, 4)), (2, List(1, 2, 3, 4)), (3, List(1, 2, 3, 4)), (4, List(1, 2, 3, 4))))
  rdd.flatMapValues(values => values)
    .filter(e => e._1 == e._2)
    .collect
    .foreach(result => println(result))
}
