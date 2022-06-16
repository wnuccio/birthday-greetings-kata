package walt.kata

import date.Date
import email.{Email, EmailGateway, EmailSender}
import friendsfile.FriendsFile
import greeting.{BirthdayGreetingsFacade, Clock, FriendRepository, GreetingsSender}

import java.io.{FileWriter, PrintWriter}
import java.time.LocalDate
import scala.io.Source

object BirthdayGreetings {

  def main(args: Array[String]): Unit = {
    val friendRepository: FriendRepository = createFriendRepository(args)
    val clockReader: Clock = createClock(args)
    val greetingsSender: GreetingsSender = createGreetingsSender(args)

    new BirthdayGreetingsFacade(friendRepository, greetingsSender, clockReader).sendGreetings()
  }

  private def createGreetingsSender(args: Array[String]) = {
    val outputFile = args(2)
    val emailSender = new EmailSender(new EmailGateway {
      val writer = new PrintWriter(new FileWriter(s"src/main/resources/$outputFile"))

      override def sendEmail(email: Email): Unit = {
        writer.append(s"email to: ${email.personName}, ${email.address.value}\n")
        writer.flush()
      }
    })
    emailSender
  }

  private def createClock(args: Array[String]): Clock = {
    val clockFile = args(1)
    new Clock {
      override def today: LocalDate = Date(Source.fromResource(clockFile).getLines().next())
    }
  }

  private def createFriendRepository(args: Array[String]): FriendRepository = {
    val inputFile = args(0)
    new FriendsFile(inputFile)
  }
}
