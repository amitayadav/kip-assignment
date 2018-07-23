package com.knoldus

case class Pet(name: String)

class PatternMatching {

  def returnWhatYouGet(valType: Any)={

    valType match{

      case s: String=> s"you gave me this string: $s"
      case i: Int=> s"thanks for the int: $i"
      case f:Float=> s"thanks for the float: $f"
      case arr1: Array[Int]=> s"an array of int: ${arr1.mkString(",")}"
      case arr2: Array[String]=> s"an array of string: ${arr2.mkString(",")}"
      case p: Pet=> s"${p.name} is a good dog "
      case list: List[Any]=> s" thanks for the list: ${list.toString()}"
      case m: Map[_,_]=> m.toString
      case _=> "other input"
    }
  }

}
/*
object PatternMatching extends App {

  val patternMatching = new PatternMatching
  println( patternMatching.returnWhatYouGet("hi"))
  println(patternMatching.returnWhatYouGet(27))
  println( patternMatching.returnWhatYouGet(27.3))
  println(patternMatching.returnWhatYouGet(Array(1, 3, 5)))
  println(patternMatching.returnWhatYouGet(Array("a", "b")))
  println(patternMatching.returnWhatYouGet(List(5, 3.0, "d")))
  println(patternMatching.returnWhatYouGet(Pet("tommy")))
  println(patternMatching.returnWhatYouGet(Map(1 -> 'a', 2 -> 'b')))
  println( patternMatching.returnWhatYouGet(27.3f))

*/
