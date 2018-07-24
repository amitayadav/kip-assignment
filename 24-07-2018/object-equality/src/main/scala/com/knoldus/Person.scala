package com.knoldus

class Person(private var name:String,private var age:Int){

  override def equals(other: Any) = other match {
    case that: Person => this.name == that.name && this.age == that.age
    case _ => false
  }

  def equalsComparison(other: Any) = other match {
    case that: Person => this.name == that.name && this.age == that.age
    case _ => false
  }
}

object Person extends App{
val person = new Person("Abhishek", 22)
val coll = collection.mutable.HashSet(person)
println(coll contains person)
person.age += 1
println(coll contains person)
println(coll.iterator contains person)

}
