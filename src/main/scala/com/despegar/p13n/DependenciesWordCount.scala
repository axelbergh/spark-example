package com.despegar.p13n

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.json4s._
import org.json4s.jackson.JsonMethods._

object DependenciesWordCount  {
  def main(args: Array[String]) {
    val logFile = "file:////develop/spark-1.6.0/README.md"
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter{line =>
      parse(""" { "numbers" : [1, 2, 3, 4] } """)
      line.contains("a")
    }.count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}