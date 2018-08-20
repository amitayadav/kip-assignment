package forms

import play.api.data.Form
import play.api.data.Forms.{mapping,email,optional,text,number}

case class User(firstname:String,middlename:Option[String],lastname:String,username:String,password:String,confirmPassword:String,phoneNo:Int,gender:String,age:Int)
class UserForm {
  val userForm = Form(
    mapping(

      "firstname"-> text,
      "middlename"-> optional(text),
      "lastname"-> text,
      "username"-> text,
      "password"-> text,
      "confirmPassword"-> text,
      "phoneNo"->  number,
      "gender"-> text.verifying("please select a value", _.nonEmpty),
      "age"-> number


    )(User.apply)(User.unapply)
  )

}
