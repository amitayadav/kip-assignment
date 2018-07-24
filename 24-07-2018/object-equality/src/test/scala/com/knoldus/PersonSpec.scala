package com.knoldus
import org.scalatest.FunSuite

class PersonSpec extends FunSuite {

  val testPerson1= new Person("Abhishek",24)
  val testPerson2= new Person("Abhishek",24)
  val testPerson3= new UpdatePerson

  test("valid input case"){
    assert(testPerson1 === testPerson2)
  }

  test("after modifying the inputs"){
    val actualResult=testPerson3.modify("ABHI")
    val expectedResult=Seq(("ABHI",23),true,true)
    assert(actualResult===expectedResult)
  }



}
