package com.despegar.p13n

import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._

object StreamingExample {
  
  val zkQuorum = "localhost:3181,localhost:4181,localhost:2181/p13n-kafka"
  val group = "streamGroup"
  val topic = "testTopic"
  val numThreads = 3
  
  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("StreamingExample")
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    ssc.checkpoint("checkpoint")
    val topicMap = Map(topic -> numThreads)
    val kafkaStream = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap)
    
    val lines = kafkaStream.map(_._2)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1L))
      .reduceByKeyAndWindow(_ + _, _ - _, Minutes(10), Seconds(10), 2)
    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()
  }
  
}