
package com.knoldus

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object OperationOnKey extends App {

  val conf = new SparkConf().setMaster("local").setAppName("spark demo")
  val sc = new SparkContext(conf)

  val rdd1: RDD[(Int, Double)] = sc.parallelize(Seq((1, 3.6)))
  val rdd2: RDD[(Int, Double)] = sc.parallelize(Seq((1, 1.1)))

  val joinedRdd = rdd1 join rdd2
  joinedRdd.map(e => (e._1, e._2._1 - e._2._2)).collect() foreach (e => print(e))

}
