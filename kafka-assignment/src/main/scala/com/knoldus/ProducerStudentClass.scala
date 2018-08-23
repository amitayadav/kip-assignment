
package com.knoldus

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

class ProducerStudentClass(topic: String, brokers: String) {

  val producerStudent = new KafkaProducer[String, Student](configuration)


  private def configuration: Properties = {
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.put("key.serializer", "com.knoldus.StudentSerializer")
    props.put("value.serializer","com.knoldus.StudentSerializer")
    props
  }


  def sendMessages(topic:String): Unit = {

   //val student=Student(1,"Amita")
    val record = new ProducerRecord[String, Student](topic, "1", Student(1,"Amita"))
    producerStudent.send(record)
    producerStudent.close()
  }

}

object ProducerStudentClass extends App {
val topic="testStudent"
  val producer = new ProducerStudentClass(brokers = "localhost:9092", topic = "testStudent")
  producer.sendMessages(topic)

}

  /*val producer = new KafkaProducer[String, String](props)

  val TOPIC="test"

  for(i<- 1 to 50){
    val record = new ProducerRecord(TOPIC, "key", s"hello $i")
    producer.send(record)
  }

  val record = new ProducerRecord(TOPIC, "key", "the end "+new java.util.Date)
  producer.send(record)

  producer.close()

}*/
