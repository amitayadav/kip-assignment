package com.knoldus.api.services

import akka.NotUsed
import com.knoldus.api.datamodels.RootJsonObject
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}


trait CustomerService extends Service {

  override final def descriptor = {
    import Service._

    named("customer")
      .withCalls(
        pathCall("/api/customer/:name", customer _),
        pathCall("/api/consume/customer", consumeCustomer _)
      )

      .withAutoAcl(true)

  }

  def customer(name: String): ServiceCall[NotUsed, String]

  def consumeCustomer: ServiceCall[NotUsed, RootJsonObject]
}
