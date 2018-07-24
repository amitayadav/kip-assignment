
package com.knoldus
import scala.util.matching.Regex

object EMail {

  def apply(user: String, domain: String) = user + "@" + domain

  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }
}

class EmailParsing{

  def emailParsing(email:String):Option[String]= {

  val regex_e_mail = "(.+@[A-Za-z0-9.]+)(?:[:;,|]+)(.*) ".r

//val eMail="knol@knoldus.in"
  email match {
  case EMail(user,domain)  =>{
    (regex_e_mail.findAllMatchIn (email))
    Some (s"E-mail =" + email + "\n" + "user =" + user + "\n" + "domain =" + domain)}
  case _ => Some("did not match with regex")
  }
 }
}

object EmailParsing extends App{

    val parsing = new EmailParsing
    println(parsing.emailParsing("knol@knoldus.in"))
    println(parsing.emailParsing("knolknoldus.in"))
}







