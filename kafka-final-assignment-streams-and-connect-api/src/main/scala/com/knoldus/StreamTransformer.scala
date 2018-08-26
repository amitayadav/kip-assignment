
package com.knoldus

import java.util.Properties
import java.util.concurrent.TimeUnit

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder}
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.KStream
import org.apache.log4j.Logger

object StreamTransformer extends App {

  val log = Logger.getLogger(this.getClass)


  val config: Properties = {
    val props = new Properties()
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "map-table-using-streams")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,"serds.employee.EmployeeDeserializer")

    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
    props
  }

  val builder = new StreamsBuilder
  val originalStreamedTable: KStream[String,String] = builder.stream("test-sqlite-jdbc-employee")
  log.info(originalStreamedTable)
  /*val streamedTable:KTable[Array[Byte], String]=originalStreamedTable.mapValues(_=> concat(_.2,_.3))
  .
*/
  /*val uppercasedWithMapValues: KStream[Array[Byte], String] = textLines.mapValues(_.toUpperCase())
  uppercasedWithMapValues.to("UppercasedTextLinesTopic")


  val originalAndUppercased: KStream[String, String] = textLines.map((_, value) => (value, value.toUpperCase()))


  originalAndUppercased.to("test-sqlite-jdbc-newperson")
*/
  val streams: KafkaStreams = new KafkaStreams(builder.build(), config)
  streams.start()

  sys.ShutdownHookThread {
    streams.close(10, TimeUnit.SECONDS)
  }

}


