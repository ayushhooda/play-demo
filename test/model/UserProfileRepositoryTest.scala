package model

import org.specs2.mutable.Specification
//import org.specs2.Specification

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class UserProfileRepositoryTest extends Specification {

    val repo = new ModelsTest[UserProfileRepository]

    val user = User(2, "xyz", "Hooda", "ayushhooda14@gmail.com")

    "User Profile Repository" should {
      "store user profile" in {
        val storeResult = Await.result(repo.repository.store(user), Duration.Inf)
        storeResult must equalTo(true)
      }
    }

  }


