package users

import play.api.data.Form
import play.api.data.Forms.{mapping, text, email}

case class UserInformation(fname: String, lname: String, email: String)

class UserForm {

  val userInfoForm = Form(mapping(
    "fname" -> text.verifying("Please fill first name", _.nonEmpty),
    "lname" -> text.verifying("Please fill last name", _.nonEmpty),
    "email" -> email
  )(UserInformation.apply)(UserInformation.unapply))

}
