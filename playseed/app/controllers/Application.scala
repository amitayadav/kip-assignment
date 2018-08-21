package controllers

import forms.{LoginForm, UserForm}
import javax.inject.Inject
import modals.{DatabaseRepo, UserRepo}
import play.api.mvc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Application @Inject()(controllerComponent: ControllerComponents,
                            userForm: UserForm,loginForm:LoginForm, dbRepo: DatabaseRepo) extends AbstractController(controllerComponent) {

  def index: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(views.html.main()))
  }

  def signUpIndex: Action[AnyContent] = Action.async{ implicit request=>
    Future.successful(Ok(views.html.signUp(userForm.userForm)))

  }

  def signUp: Action[AnyContent] = Action.async { implicit request =>

    userForm.userForm.bindFromRequest.fold(
      formWithError => {
        Future.successful(Ok(views.html.signUp(formWithError)))
      },
      data => {
        dbRepo.get(data.username).flatMap {
          optionalUser =>
            optionalUser.fold {
              val dbPayload: UserRepo= UserRepo(0,data.firstname,data.middlename,data.lastname,data.username,
                data.password.password,data.phoneNo,data.gender,data.age,data.hobbies)
              dbRepo.store(dbPayload).map {_=>
               Ok(views.html.normalUserProfile())

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
    loginForm.loginForm.bindFromRequest.fold(
      formWithError => {
        Future.successful(BadRequest(s"${formWithError.errors}"))
      },
      data => {
        dbRepo.get(data.username).map{
          optionalUser=>
            optionalUser.fold{
              NotFound("user does not exits")
            } { loginuser => {
              if (loginuser.password == data.password) {
                Redirect(routes.Application.normalProfile())
              }
              else {
                Ok("invalid username or passsword")
              }

              }
            }
        }
      }
    )
  }


  def getUser(username: String) :Action[AnyContent] = Action.async { implicit request =>
    dbRepo.get(username).map{
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

    Future.successful(Ok(views.html.forgetPassword(userForm.userForm)))
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
  def addAssignment: Action[AnyContent] = Action.async{ implicit request =>
    Future.successful(Ok(views.html.addAssignments()))
  }
  def viewUser: Action[AnyContent] = Action.async{ implicit request =>
    Future.successful(Ok(views.html.viewUser()))
  }

}
