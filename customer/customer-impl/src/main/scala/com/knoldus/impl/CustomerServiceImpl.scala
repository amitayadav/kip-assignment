package com.knoldus.impl

import akka.NotUsed
import com.knoldus.api.datamodels.RootJsonObject
import com.knoldus.api.services.{CustomerRecordService, CustomerService}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.{ExecutionContext, Future}

class CustomerServiceImpl(customerRecordService: CustomerRecordService)(implicit ec: ExecutionContext) extends CustomerService {

  private final val log: Logger = LoggerFactory.getLogger(classOf[CustomerServiceImpl])

  override def customer(name: String): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    log.info(s"Customer with name $name, greeted.")
    Future.successful(s"Welcome, $name")
  }

  override def consumeCustomer = ServiceCall { _ =>
    val result: Future[RootJsonObject] = customerRecordService.getGuest().invoke()
    result.map(response => response)
  }
}
