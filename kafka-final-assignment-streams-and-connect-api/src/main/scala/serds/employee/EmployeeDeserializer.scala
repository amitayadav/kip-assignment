package serds.employee

import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util

import Modals.Employee
import net.liftweb.json.{DefaultFormats, parse}
import org.apache.kafka.common.serialization.Deserializer
import org.apache.log4j.Logger

class EmployeeDeserializer extends Deserializer[Employee]{
  val log = Logger.getLogger(this.getClass)

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
  }

  override def close(): Unit = {
  }

  override def deserialize(topic: String, bytes: Array[Byte]): Employee = {

    try {
      implicit val formats = DefaultFormats

      val jsonString = """
  {"schema":{"type":"struct",
 |  "fields":[{"type":"int64",
 |  "optional":false,
 |  "field":"id"},
 |  {"type":"string","optional":true,"field":"firstname"},{"type":"string","optional":true,"field":"lastname"},{"type":"string","optional":true,"field":"gender"}],"optional":false,"name":"employee"},"payload":{"id":1,"firstname":"amita","lastname":"yadav","gender":"female"}}
 |  """

      val jValue = parse(jsonString)

      val employee = jValue.extract[Employee]
      employee
    }
    catch {
      case ex: Exception => throw new Exception(ex.getMessage)
    }
  }

}