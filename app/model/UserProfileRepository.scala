package model

import javax.inject.Inject

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import slick.lifted.ProvenShape
import slick.lifted.ProvenShape.proveShapeOf

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserProfileRepository @Inject()
(protected val dbConfigProvider: DatabaseConfigProvider) extends
  UserProfileRepositoryTable with UserProfileBaseRepository


trait UserProfileBaseRepository {
  self: UserProfileRepositoryTable =>

  import profile.api._

  def store(user: User): Future[Boolean] = {
    db.run(userProfileQuery += user) map (_ > 0)
  }

  def findByEmail(email: String): Future[Option[User]] = {
    val queryResult = userProfileQuery.filter(_.email.toLowerCase === email.toLowerCase).result.headOption
    db.run(queryResult)
  }

}

trait UserProfileRepositoryTable extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._
  val userProfileQuery: TableQuery[UserProfile] = TableQuery[UserProfile]

  class UserProfile(tag: Tag) extends Table[User](tag, "user_details") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def fname: Rep[String] = column[String]("fname")

    def lname: Rep[String] = column[String]("lname")

    def email: Rep[String] = column[String]("email")

    def * : ProvenShape[User] = (id, fname, lname, email) <>(User.tupled, User.unapply)
  }
}
