package com.knoldus.api.services

import akka.NotUsed
import com.knoldus.api.datamodels.RootJsonObject
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

trait CustomerRecordService extends Service {

  override final def descriptor = {
    import Service._

    named("customer-record-service")
      .withCalls(
        pathCall("/deepakmehra10/227713bfa0aded2196059d6a623ea4e2/raw", getGuest _)
      ).withAutoAcl(true)
  }

  def getGuest(): ServiceCall[NotUsed, RootJsonObject]
}
