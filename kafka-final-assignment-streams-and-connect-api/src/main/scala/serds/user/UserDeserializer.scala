package serds.user

import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util

import Modals.{User}
import net.liftweb.json.{DefaultFormats, parse}
import org.apache.kafka.common.serialization.Deserializer
import org.apache.log4j.Logger

class UserDeserializer extends Deserializer[User] {

  val log = Logger.getLogger(this.getClass)

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
  }

  override def close(): Unit = {
  }

  override def deserialize(topic: String, bytes: Array[Byte]): User = {

    try {

      implicit val formats = DefaultFormats

      val jsonString = """
  {"schema":{"type":"struct",
  "fields":[{"type":"int64",
  "optional":false,
  "field":"id"},
  {"type":"string","optional":true,"field":"name"},{"type":"string","optional":true,"field":"lastname"}],
  "optional":false,"name":"user"},
  "payload":{"id":1,"name":"Ms.amita yadav"}}
  """


      val jValue = parse(jsonString)


      val user = jValue.extract[User]
      user
    }
    catch {
      case ex: Exception => throw new Exception(ex.getMessage)
    }
  }

}
