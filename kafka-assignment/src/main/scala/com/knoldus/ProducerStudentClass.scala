
package com.knoldus

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.log4j.Logger

class ProducerStudentClass(topic: String, brokers: String) {

  val producerStudent = new KafkaProducer[String, Student](configuration)
  val log = Logger.getLogger(this.getClass)


  private def configuration: Properties = {
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer","com.knoldus.StudentSerializer")
    props
  }


  def sendMessages(topic:String): Unit = {


    producerStudent.send(new ProducerRecord[String, Student](topic, "1", Student(1,s"Amita")))
    log.info("Message produced.....")
    producerStudent.close()
  }

}

object ProducerStudentClass extends App {
val topic="testStudent1"
  val producer = new ProducerStudentClass(brokers = "localhost:9092", topic = topic)
  producer.sendMessages(topic)

}
