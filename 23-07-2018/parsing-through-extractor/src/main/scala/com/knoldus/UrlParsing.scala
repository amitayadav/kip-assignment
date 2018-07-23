
package com.knoldus

object URL{

    def apply(protocol:String,domain:String,path:String, params:Map[String,String]):String = {
      protocol+ "://"+ domain +"/"+ path + "?" + params
        .map(element=>element._1+"="+element._2+"&")
    }

    def unapply(url:String):Option[(String , String, String,Map[String,String])] = {


      val protocol = url.split("://")
      if(protocol.length ==2 ) {
        val domain = protocol(1) split("/", 2)
        val path = domain(1).split("\\?")
        val params = path(1).split("&").map(ele => {
          val mapping = ele split ("=")
          mapping(0) -> mapping(1)
        }).toMap
        Some(protocol(0), domain(0), path(0), params)
      }
      else None

    /*  if (parts.length == 2){ Some(parts(0),parts(1) split "/")
        if (parts.length == 2) Some(parts(0),parts(1) split "?") else None)
      }*/

    }


}
class UrlParsing{

 ///val url= URL("https","aws.amazon.com","/console/home",Map("state"->"hash","isauthcode"->"true","code"->"112"))
  def extractingDetails(url:String):String= {
    url match {
      case URL(protocol, domain, path, params) =>
        "protocol =" + protocol + "\n domain =" + domain + "\n path =" + path + "\n params =" + params
      //case URL(url) =>
      case _ => "Not a valid URL"
    }
  }
}
 object Test extends App {

   val t = new UrlParsing
   println(t.extractingDetails("https:/aws.amazon.com"))
 }


