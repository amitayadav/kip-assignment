package com.knoldus

import java.util.logging.Logger

import org.apache.log4j.Logger

object LogDemo extends App{

  val logger= Logger.getLogger(this.getClass)
  logger.info("hello i m amita")

}
