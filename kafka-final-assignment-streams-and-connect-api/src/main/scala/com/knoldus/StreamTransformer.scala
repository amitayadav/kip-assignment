
package com.knoldus

import java.util.Properties
import java.util.concurrent.TimeUnit

import Modals.{Employee, User}
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.streams.{KafkaStreams, KeyValue, StreamsBuilder, StreamsConfig}
import org.apache.kafka.common.serialization.{Serde, Serdes}
import org.apache.kafka.streams.kstream.{KStream, Produced}
import org.apache.log4j.Logger
import serds.EmployeeSerdes
import serds.user.{UserDeserializer, UserSerializer}

object StreamTransformer extends App {

  val log = Logger.getLogger(this.getClass)


  val config: Properties = {
    val props = new Properties()
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "map-table-using-streams")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,classOf[EmployeeSerdes])

    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
    props
  }

  val builder = new StreamsBuilder
  val originalStreamedTable: KStream[String,Employee] = builder.stream("test-sqlite-jdbc-employee")

  val userSerializer = new UserSerializer
  val userDeserializer = new UserDeserializer()
  val userSerde: Serde[User] = Serdes.serdeFrom(userSerializer, userDeserializer)



  val userStreamTable: KStream[String, User] = originalStreamedTable.map((key, employee) => {
    var prefix = ""
    if(employee.gender == "Male") {
      prefix = "Mr."
    }
    else {
      prefix = "Ms."
    }
    val user = User(employee.id, s"$prefix ${employee.firstname} ${employee.lastname}")
    KeyValue.pair(key, user)
  })
  userStreamTable.to( "user", Produced.`with`(Serdes.String(), userSerde))


  originalStreamedTable.print()



  val streams: KafkaStreams = new KafkaStreams(builder.build(), config)
  streams.start()

  sys.ShutdownHookThread {
    streams.close(10, TimeUnit.SECONDS)
  }

}


