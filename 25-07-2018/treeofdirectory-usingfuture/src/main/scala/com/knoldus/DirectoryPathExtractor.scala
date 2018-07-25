package com.knoldus

import java.io.File
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future
import scala.io.Source
import scala.util.{Failure, Success}

class DirectoryPathExtractor {

  def pathExtractor(directoryPath: String)= Future {

      val folder = new File(directoryPath)
      if (folder.exists() && folder.isDirectory)
        folder.listFiles().toList.map(
          file => file.getName
        )

    }
  }






object DirectoryPathExtractor extends App{
val directoryaccessor= new DirectoryPathExtractor

  val  listOfFiles=Future{directoryaccessor.pathExtractor("/home/knoldus/Folder1")}
  listOfFiles.onComplete {
    case Success(result) => println(result)
    case Failure(ex) => print(ex.getMessage)
  }

}


