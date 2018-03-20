package controllers

import javax.inject.Inject

import model.{User, UserInfoRepo, UserProfileRepository}
import play.api.i18n.I18nSupport
import play.api.mvc._
import users.UserForm

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

class HomeControl @Inject()(userRepo: UserProfileRepository, userForm: UserForm, userInfoRepo: UserInfoRepo,cc: ControllerComponents)
  extends AbstractController(cc) with I18nSupport{

  def index: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
//    throw new Exception
    Future.successful(Ok(views.html.index(userForm.userInfoForm)))
  }

  def storeData: Action[AnyContent] = Action.async { implicit request =>
    userForm.userInfoForm.bindFromRequest().fold(
      formWithError => {
        Future.successful(BadRequest(views.html.index(formWithError)))
      },
      data => {
        userInfoRepo.getUser(data.email).flatMap {
          optionalRecord => optionalRecord.fold {
            val record = userInfoRepo.UserInfo(data.fname, data.lname, data.email)
            userInfoRepo.store(record).map {
              _ => Ok("stored")
            }
          } { _ => Future.successful(InternalServerError("user already exists"))
          }
        }
      }
    )
  }

  def getData(email: String): Action[AnyContent] = Action.async {
    userInfoRepo.getUser(email).map { optionalRecord =>
      optionalRecord.fold {
        NotFound("Oops!! user not found")
      }
      {
        record => Ok(s"User's fullname is ${record.fname} ${record.lname}")
      }
    }
  }

  def storeInDb = Action.async { implicit request =>
    userForm.userInfoForm.bindFromRequest().fold(
      formWithError => {
        Future.successful(BadRequest(views.html.index(formWithError)))
      },
      data => {
        userRepo.store(User(0, data.fname, data.lname, data.email)).map {
          case true => Ok("stored")
          case false => Ok("not stored")
        }
      })
  }

}
