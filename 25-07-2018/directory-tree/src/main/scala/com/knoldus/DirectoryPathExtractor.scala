package com.knoldus

import java.io.File

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source
import scala.util.{Failure, Success}

class DirectoryPathExtractor {

    def pathExtractor(directoryPath: String):Future[List[File]] = Future{
      val folder = List(new File(directoryPath))
      getSubFiles(folder)
    }

    def getSubFiles(files: List[File],output:List[File]=Nil):List[File]={
      /*if (folder.exists() && folder.isDirectory)
        folder.listFiles().toList.map(file => file.getName)*/
        files match {
          case x :: tail => getSubFiles(tail ::: x.listFiles.filter(_.isDirectory).toList,
            output ::: x.listFiles.filter(_.isFile).toList)
          case _ => output
        }
    }


}






object DirectoryPathExtractor extends App {
  val directoryaccessor = new DirectoryPathExtractor

  val listOfFiles = directoryaccessor.pathExtractor("/home/knoldus/Folder1")

  Thread.sleep(5000)

 // println(s"....$listOfFiles")
  listOfFiles.onComplete {
    case Success(result) => println(listOfFiles)
    case Failure(ex) => print(ex.getMessage)
  }

  Thread.sleep(5000)
}

