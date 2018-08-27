
package com.knoldus

import java.util.Properties
import java.util.concurrent.TimeUnit

import Modals.{Employee, User}
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.streams.{KafkaStreams, KeyValue, StreamsBuilder, StreamsConfig}
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.kstream.{KStream, Produced}
import org.apache.log4j.Logger
import serds.{CustomJsonDeserializer, CustomJsonSerializer, JsonSerdes}

object StreamTransformer extends App {

  val log = Logger.getLogger(this.getClass)

  val config: Properties = {
    val props = new Properties()
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "map-table-using-streams")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,classOf[JsonSerdes])
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
    props
  }

  val builder = new StreamsBuilder()
  val originalStreamed: KStream[String,Employee] = builder.stream("test-sqlite-jdbc-employee")

  val userStream: KStream[String, User] = originalStreamed.map((key, employee:Employee) =>{
    val genderPrefix=employeeToPerson(employee)
    val user = User(employee.id, s"$genderPrefix ${employee.firstname} ${employee.lastname}")
    KeyValue.pair(key, user)
  })

  private def employeeToPerson(employee: Employee):String= {
   val pre:String =employee.gender.toLowerCase match {
     case male => "Mr."
     case female => "Ms."
     case _ => ""
   }

       pre
  }

  val jsonSerializer = new CustomJsonSerializer[User]
  val jsonDeserializer= new CustomJsonDeserializer[User]
  val userSerde = Serdes.serdeFrom(jsonSerializer,jsonDeserializer)


  userStream.to( "test-sqlite-jdbc-user", Produced.`with`(Serdes.String(), userSerde))

  originalStreamed.print()

  val streams: KafkaStreams = new KafkaStreams(builder.build(), config)
  streams.start()

  sys.ShutdownHookThread {
    streams.close(10, TimeUnit.SECONDS)
  }

}
