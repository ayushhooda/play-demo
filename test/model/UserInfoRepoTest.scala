//package model
//
//import akka.Done
//import org.specs2.mutable.Specification
//import play.api.Application
//import play.api.inject.guice.GuiceApplicationBuilder
//
//import scala.concurrent.Await
//import scala.concurrent.duration.Duration
//import scala.reflect.ClassTag
//
//class UserInfoRepoTest extends Specification {
//
//  val repo = new ModelsTest[UserInfoRepo]
//
//  "user info Repository" should {
//    "store associate detail of a user" in {
//      val user = repo.repository.UserInfo("ayush", "hooda", "ayushhooda14@gmail.com")
//      val storeResult = Await.result(repo.repository.store(user), Duration.Inf)
//      storeResult must equalTo(Done)
//    }
//  }
//
//}
