package com.knoldus

class Person( var name: String,var age: Int) {

  override def equals(other: Any) = other match {
    case that: Person => this.name == that.name && this.age == that.age
    case _ => false
  }

  def equalsComparison(other: Any) = other match {
    case that: Person => this.name == that.name && this.age == that.age
    case _ => false
  }
}

class UpdatePerson{
  val person = new Person("Abhishek", 22)
  val coll = collection.mutable.HashSet(person)
  println(coll contains person)
  def modify(newName:String):Seq[Any]= {
    person.age += 1
    person.name=newName
    //println(person)
    Seq((person.name,person.age),coll contains person,coll.iterator contains person)

  }

}
