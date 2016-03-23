package com.despegar.p13n

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object WordCount  {
  def main(args: Array[String]) {
    val logFile = "file:////develop/spark-1.6.0/README.md"
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}