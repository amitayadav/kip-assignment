package com.knoldus

class PatternMatchingWithRecursion {

  def maximum(xs: List[Int]): Int = xs match {
    case Nil => throw new NoSuchElementException("The list is empty")
    case x :: Nil => x
    case x :: tail => x.max(maximum(tail))
  }

  def length[A](list : List[A]) : Int = list match {
    case Nil => 0
    case _ :: tail => 1 + length(tail)

  }
  def nthElement[A](n:Int,l: List[A]): A = l match {
    case tail if (tail.length == n) => tail.head
    case _ :: tail => nthElement(n, tail)
    case _ => throw new NoSuchElementException
  }

  def minimum(xs: List[Int]): Int = xs match {
    case Nil => throw new NoSuchElementException("The list is empty")
    case x :: Nil => x
    case x :: tail => x.min(minimum(tail))
  }

}
