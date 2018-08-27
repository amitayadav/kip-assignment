package serds

import Modals.User
import java.util
import org.apache.kafka.common.serialization.{Deserializer, Serde, Serializer}
import serds.user.{UserDeserializer, UserSerializer}

class PersonSerdes extends Serde[User]{
  private val UserSerializer = new UserSerializer
  private val UserDeserializer: UserDeserializer = new UserDeserializer()

  def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
    serializer.configure(configs, isKey)
    deserializer.configure(configs, isKey)
  }

  def close(): Unit = {
    serializer.close()
    deserializer.close()
  }

  def serializer: Serializer[User] = UserSerializer

  def deserializer: Deserializer[User] = UserDeserializer

}
