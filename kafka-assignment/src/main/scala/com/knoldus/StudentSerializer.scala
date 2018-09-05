package com.knoldus


import java.io.{ByteArrayOutputStream, ObjectOutputStream}
import java.util
import org.apache.kafka.common.serialization.Serializer


class StudentSerializer extends Serializer[Student] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
  }

  override def serialize(topic: String, data: Student): Array[Byte] = {

    try {
      val byteOutputStream = new ByteArrayOutputStream()
      val objectSerialized = new ObjectOutputStream(byteOutputStream)
      objectSerialized.writeObject(data)
      byteOutputStream.toByteArray
    }
    catch {
      case ex: Exception => throw new Exception(ex.getMessage)
    }
  }

  override def close(): Unit = {
  }

}