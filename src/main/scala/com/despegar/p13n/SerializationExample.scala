package com.despegar.p13n

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

class SerializationExample {
  val context = new SparkContext(new SparkConf().setAppName("test").setMaster("local[*]"))
  val rdd = context.parallelize(List(1, 2, 3))

  def foo = {
    val doubles = rdd.map(double).collect
    doubles.foreach(println)
  }

  def double(n: Int) = 2 * n
}