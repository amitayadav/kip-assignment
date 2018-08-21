package forms

import play.api.data.Form
import play.api.data.Forms.{mapping,optional,text,number,list}

case class Password(password: String,confirmPassword:String)

case class User(firstname:String,middlename:Option[String],lastname:String,username:String,
                password:Password,phoneNo:Int,gender:String,age:Int,hobbies:List[String])
class UserForm {
  val userForm = Form(
    mapping(

      "firstname"-> text,
      "middlename"-> optional(text),
      "lastname"-> text,
      "username"-> text,
      "passwordGroup"-> mapping(
        "password"-> text.verifying("password must not be empty",_.nonEmpty),
        "confirmPassword" -> text.verifying("confirm password must not be empty",_.nonEmpty)
      )(Password.apply)(Password.unapply)
        .verifying("password and confirm password must match",passwordGroup=>
          passwordGroup.password == passwordGroup.confirmPassword),

      "phoneNo"->  number,
      "gender"-> text.verifying("please select a value", _.nonEmpty),
      "age"-> number,
      "hobbies" -> list(text)


    )(User.apply)(User.unapply)
  )

}
