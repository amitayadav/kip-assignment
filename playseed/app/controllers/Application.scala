package controllers

import akka.Done
import forms.UserForm
import javax.inject.Inject
import modals.CacheRepo
import play.api.mvc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Application @Inject()(controllerComponent: ControllerComponents,
                            userForm: UserForm, cacheRepo: CacheRepo) extends AbstractController(controllerComponent) {

  def index: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(views.html.main()))
  }

  def signUpIndex: Action[AnyContent] = Action.async{ implicit request=>
    Future.successful(Ok(views.html.signUp()))

  }

  def signUp: Action[AnyContent] = Action.async { implicit request =>

    userForm.userForm.bindFromRequest.fold(
      formWithError => {
        Future.successful(BadRequest(s"${formWithError.errors}"))
      },
      data => {
        cacheRepo.get(data.username).flatMap {
          optionalUser =>
            optionalUser.fold {
              cacheRepo.store(data).map {
                case Done => Ok(views.html.normalUserProfile())
                case _ => InternalServerError("something went wrong")
              }

            } {
              _ => Future.successful(Ok("user already exists"))
            }
        }

      }
    )
  }

  def signInIndex: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(views.html.signIn()))
  }


  def signIn: Action[AnyContent] = Action.async { implicit request =>
    userForm.userForm.bindFromRequest.fold(
      formWithError => {
        Future.successful(BadRequest(s"${formWithError.errors}"))
      },
      data => {
        cacheRepo.get(data.username).flatMap{
          optionalUser =>
            optionalUser.fold{
              cacheRepo.store(data).map {
                case Done => Ok("login successful")
                case _ => InternalServerError("something went wrong")
              }
              }{_=> Future.successful(Ok("user does not exits"))
            }
        }
      }
    )

  }
  def getUser(username: String) :Action[AnyContent] = Action.async { implicit request =>
    cacheRepo.get(username).map{
      optionaluser=>
        optionaluser.fold{
          NotFound("user does not exists")
        }{
          user =>
            Ok(s"${user.firstname},${user.lastname},${user.age},${user.gender},${user.phoneNo}")
        }
    }
  }




  def resetPassword: Action[AnyContent] = Action.async { implicit request =>

    Future.successful(Ok(views.html.forgetPassword()))
  }

  def normalProfile: Action[AnyContent] = Action.async{ implicit request =>
    Future.successful(Ok(views.html.normalUserProfile()))
  }
  def profile: Action[AnyContent] = Action.async{ implicit request =>
    Future.successful(Ok(views.html.profile()))
  }
  def adminProfile: Action[AnyContent] = Action.async{ implicit request =>
    Future.successful(Ok(views.html.adminProfile()))
  }
  def viewAssignment: Action[AnyContent] = Action.async{ implicit request =>
    Future.successful(Ok(views.html.viewAssignments()))
  }

}
