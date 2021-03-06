package com.knoldus
import org.scalatest.FunSuite

class PersonSpec extends FunSuite {

  val testPerson1= new Person("Amita",24)
  val testPerson2= new Person("Amita",24)
  val testPerson3= new UpdatePerson

  test("valid input case"){
    println(testPerson1.hashCode()+ "llkiooo"+ testPerson2.hashCode())
    assert(testPerson1 == testPerson2)
  }

  test("after modifying the inputs"){
    val actualResult=testPerson3.modify("AMI")
    val expectedResult=Seq(("AMI",23),true,true)
    assert(actualResult===expectedResult)
  }



}
