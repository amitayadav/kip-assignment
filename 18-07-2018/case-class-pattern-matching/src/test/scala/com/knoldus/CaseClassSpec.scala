package com.knoldus

import org.scalatest.FunSuite


class CaseClassSpec extends FunSuite {
    val test = new ExamResult

    val studentList: List[Student] = List(Student(1, "Bob", 11, 'A'), Student(2, "Dab", 10, 'B'), Student(3, "Tab", 10, 'C'), Student(4, "Ankita", 11, 'C'))
    val studentScore: List[ScoreCard] = List(ScoreCard(1, 88, 72, 49), ScoreCard(2, 67, 78, 90), ScoreCard(3, 76, 47, 98), ScoreCard(4, 89, 87, 90))

    test("Testing for top 3 student of class") {
      val actualResult = test.findFirstThreeToppers(studentList: List[Student], studentScore: List[ScoreCard])
      val expectedResult = List((4,"Ankita"), (2,"Dab"), (3,"Tab"))
      assert(actualResult === expectedResult)

    }

    test("Testing For the subject Topper") {
      val actualResult = test.findSubjectTopper("subject1", studentList, studentScore)
      val expectedResult = (4,"Ankita",89.0,87.0,90.0,266.0)
      assert(actualResult === expectedResult)
    }

    test("Testing For The MarkSheet") {
      val actualResult = test.displayMarksheet(3, studentList, studentScore)
      val expectedResult = "ID : 3  Name : Tab  DIVISION-10 SECTION : C MARKS :  SUBJECTS :  SUBJECT1 : 76.0 SUBJECT2 : 47.0 SUBJECT3 : 98.0 TOTAL MARKS : 221.0"
      assert(actualResult === expectedResult)
    }


}
