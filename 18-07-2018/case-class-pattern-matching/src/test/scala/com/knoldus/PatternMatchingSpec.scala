
package com.knoldus

import org.scalatest.FunSuite

class PatternMatchingSpec extends FunSuite {

  val testObj = new PatternMatching
  //val objCaseClass = Pet("Tommy", "Dog")

  test("Testing for String Value") {
    val actualResult = testObj.returnWhatYouGet("Amita")
    val expectedResult = "you gave me this string: Amita"
    assert(actualResult === expectedResult)
  }

  test("Testing for Integer Value") {
    val actualResult = testObj.returnWhatYouGet(5)
    val expectedResult = "thanks for the int: 5"
    assert(actualResult === expectedResult)
  }

  test("Testing for Float Value") {
    val actualResult = testObj.returnWhatYouGet(5.0f)
    val expectedResult = "thanks for the float: 5.0"
    assert(actualResult === expectedResult)
  }

  test("Testing for Array[Int]") {
    val actualResult = testObj.returnWhatYouGet(Array(1, 2, 3, 4, 5))
    val expectedResult = "an array of int: 1,2,3,4,5"
    assert(actualResult === expectedResult)
  }

  test("Testing for Array[String]") {
    val actualResult = testObj.returnWhatYouGet(Array("Apple", "Orange", "Banana"))
    val expectedResult = "an array of string: Apple,Orange,Banana"
    assert(actualResult === expectedResult)
  }

  test("Testing for Pet") {
    val actualResult = testObj.returnWhatYouGet(Pet("Tommy"))
    val expectedResult = "Tommy is a good dog "
    assert(actualResult === expectedResult)
  }

  test("Testing for List[Any]") {
    val actualResult = testObj.returnWhatYouGet(List(1, 2, 3, 4))
    val expectedResult = " thanks for the list: List(1, 2, 3, 4)"
    assert(actualResult === expectedResult)
  }

  test("Testing for Map") {
    val actualResult = testObj.returnWhatYouGet(Map(1 -> "One", 2 -> "Two", 3 -> "Three"))
    val expectedResult = "Map(1 -> One, 2 -> Two, 3 -> Three)"
    assert(actualResult === expectedResult)
  }

}





