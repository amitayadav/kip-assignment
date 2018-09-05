
package serds

import java.util
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.kafka.common.serialization.Deserializer
import org.apache.log4j.Logger
import scala.reflect.{ClassTag, classTag}

class CustomJsonDeserializer[T >: Null : ClassTag] extends Deserializer[T] {

  val log = Logger.getLogger(this.getClass)
  val mapper = new ObjectMapper
  mapper.registerModule(DefaultScalaModule)

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
  }

  override def close(): Unit = {
  }

  override def deserialize(topic: String, data: Array[Byte]): T = data match {
//    case null => null
    case _ =>
      try {
        mapper.readValue(data, classTag[T].runtimeClass.asInstanceOf[Class[T]])
      } catch {
        case e: Exception =>
          val jsonStr = new String(data, "UTF-8")
          log.warn(s"Failed parsing ${jsonStr}", e)
          null
      }
  }

}
