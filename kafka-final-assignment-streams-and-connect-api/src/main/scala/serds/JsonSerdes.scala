
package serds

import java.util
import Modals.Employee
import org.apache.kafka.common.serialization.{Deserializer, Serde, Serializer}

class JsonSerdes extends Serde[Employee] {

  def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
    serializer.configure(configs, isKey)
    deserializer.configure(configs, isKey)
  }

   def close(): Unit = {
    serializer.close()
    deserializer.close()
  }

  override def serializer: Serializer[Employee] = new CustomJsonSerializer[Employee]

  override def deserializer: Deserializer[Employee] = new CustomJsonDeserializer[Employee]
}

