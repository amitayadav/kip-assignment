package com.knoldus.impl

import com.knoldus.api.services.{CustomerRecordService, CustomerService}
import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._
import play.api.libs.ws.ahc.AhcWSComponents

class CustomerLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new CustomerApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new CustomerApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[CustomerService])
}

abstract class CustomerApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  override lazy val lagomServer = serverFor[CustomerService](wire[CustomerServiceImpl])

  lazy val customerRecordService: CustomerRecordService = serviceClient.implement[CustomerRecordService]
}
