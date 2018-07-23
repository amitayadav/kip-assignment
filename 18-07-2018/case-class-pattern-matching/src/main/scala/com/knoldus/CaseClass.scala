package com.knoldus

case class Student(id :Int, name :String, division :Int, section :Char)

case class ScoreCard(id :Int, subject1 :Float, subject2 :Float, subject3 :Float, totalMarks :Float)

object ScoreCard{
  def apply(id :Int, subject1 :Float, subject2 :Float, subject3 :Float) :ScoreCard = {
    val totalMarks = subject1 + subject2 + subject3
    new ScoreCard(id, subject1, subject2, subject3, totalMarks)

  }

}
class ExamResult {


  def findFirstThreeToppers(list1: List[Student], list2: List[ScoreCard]): List[Any] = {

      val topperList = list2.sortWith(_.totalMarks > _.totalMarks)

      val toppeName= topperList match {
        case x :: xs :: t :: rest => x
        case _ => "Default case"
      }

      /*val topperName = topperList.map {
        sid => list1.filter(_.id == sid).head
      }
      topperName.map { e =>
        (e.id, e.name)
      }
    }
    else
      throw new IllegalArgumentException*/
  }

  def findSubjectTopper(subject :String , list1: List[Student], list2: List[ScoreCard]): (Int, String, Float, Float, Float, Float) = {

    val subjectTopper = subject match {
      case "subject1" => list2.sortWith (_.subject1 > _.subject1).head
      case "subject2" => list2.sortWith (_.subject2 > _.subject2).head
      case "subject3" => list2.sortWith (_.subject3 > _.subject3).head
    }
    val topperDetails = list1.filter(_.id == subjectTopper.id).head
    (subjectTopper.id, topperDetails.name, subjectTopper.subject1, subjectTopper.subject2, subjectTopper.subject3, subjectTopper.totalMarks)
  }



  def displayMarksheet (sid :Int, list1 :List[Student], list2 :List[ScoreCard]) :String ={

    val scoreCard = list2.filter(_.id == sid).head
    val studentInfo = list1.filter(_.id == sid).head
    s"ID : $sid " +
    s" Name : ${studentInfo.name} " +
    s" DIVISION-${studentInfo.division}" +
    s" SECTION : ${studentInfo.section}" +
    s" MARKS : " +
    s" SUBJECTS : " +
    s" SUBJECT1 : ${scoreCard.subject1}" +
    s" SUBJECT2 : ${scoreCard.subject2}" +
    s" SUBJECT3 : ${scoreCard.subject3}" +
    s" TOTAL MARKS : ${scoreCard.totalMarks}"
  }


}

