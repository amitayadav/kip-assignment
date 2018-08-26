package serds.user

import java.io.{ByteArrayOutputStream, ObjectOutputStream}
import java.util

import Modals.User
import org.apache.kafka.common.serialization.Serializer

class UserSerializer extends Serializer[User]{
  def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
  }

  def serialize(topic: String, data: User): Array[Byte] = {

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


  def close(): Unit = {
  }

}
