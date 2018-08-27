package serds

import java.util

import Modals.Employee
import org.apache.kafka.common.serialization.{Deserializer, Serde, Serializer}
import serds.employee.{EmployeeDeserializer, EmployeeSerializer}

class EmployeeSerdes extends Serde[Employee] {

  private val employeeSerializer: EmployeeSerializer = new EmployeeSerializer
  private val employeeDeserializer: EmployeeDeserializer = new EmployeeDeserializer()

  def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
    serializer.configure(configs, isKey)
    deserializer.configure(configs, isKey)
  }

   def close(): Unit = {
    serializer.close()
    deserializer.close()
  }

   def serializer: Serializer[Employee] = employeeSerializer

  def deserializer: Deserializer[Employee] = employeeDeserializer
}

