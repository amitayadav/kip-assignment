
package com.knoldus

import java.util.{Collections, Properties}

import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}
import org.apache.log4j.Logger


class ConsumerStudentClass(brokers: String, topic: String, groupId: String) {

  val consumer = new KafkaConsumer[String, Student](configuration)

  val log = Logger.getLogger(this.getClass)

  private def configuration: Properties = {
    val props = new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer","com.knoldus.StudentDeserializer")
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
    props
  }

  def receiveMessages(topic:String): Unit = {
    consumer.subscribe(Collections.singletonList(topic))
      val records: ConsumerRecords[String, Student] = consumer.poll(5000)
      for (record <- records.asScala) {
        log.info(s"received message-\n key: ${record.key} value: ${record.value} \n")

    }
  }
}

object ConsumerStudentClass extends App {

  val topic = "testStudent"

  val consumer = new ConsumerStudentClass(brokers = "localhost:9092", topic = "testStudent", groupId = "Details")
  consumer.receiveMessages(topic)

}

/*
object ConsumerStudentClass extends App{

  import java.util.Properties

  val TOPIC="test"

  val  props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")

  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id", "something")

  val consumer = new KafkaConsumer[String, String](props)

  consumer.subscribe(util.Collections.singletonList(TOPIC))

  while(true){
    val records=consumer.poll(100)
    for (record<-records.asScala){
      println(record)
    }
  }

}
*/
