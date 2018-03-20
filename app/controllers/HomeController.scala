package controllers

import java.lang.ProcessBuilder.Redirect
import javax.inject._

import model.User
import play.api._
import play.api.data.Forms._
import play.api.data._
import play.api.i18n.I18nSupport
import play.api.mvc._


/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
//  def index() = Action { implicit request: Request[AnyContent] =>
//    Ok(views.html.index("Ayush"))
//  }

//  implicit val message = cc.messagesApi
//  // Mapping
//  val userForm: Form[User] = Form(mapping(
//    "name" -> text,
//    "age" -> number
//  )(User.apply)(User.unapply))
//
//  val listOfUser: List[User] = List(User("Ayush", 23), User("Sameer", 24))
//
//  def user() = Action { implicit request: Request[AnyContent] =>
//    Ok(views.html.user(userForm))
//  }
//
//  def output() = Action { request =>
//    request.session.get("connected").map { user =>
//      Ok(views.html.output(user)).flashing("success" -> "congo")
//    }.getOrElse {
//      Ok("Oops, you are not connected").flashing("failure" -> "oops")
//    }
//  }
//
//  def createForm() = Action { implicit request: Request[AnyContent] =>
//    println("should come here")
//    userForm.bindFromRequest().fold(
//      formWithError => BadRequest(views.html.user(formWithError)),
//      data => {
//        Redirect(routes.HomeController.output()).withSession("connected" -> data.name)
//      }
//    )
//  }

}
