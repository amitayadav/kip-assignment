package modals

import akka.Done
import forms.User
import javax.inject.Inject
import play.api.cache.AsyncCacheApi


import scala.concurrent.Future

class CacheRepo @Inject()(cache: AsyncCacheApi){

  def store(user: User): Future[Done]={
    cache.set(user.username,user)

  }

  def get(username: String): Future[Option[User]]={
    cache.get(username)
  }


}
