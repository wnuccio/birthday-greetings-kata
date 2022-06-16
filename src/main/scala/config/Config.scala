package walt.kata
package config

import date.Date
import email.{Email, EmailGateway, EmailSender}
import friendsfile.FriendsFile
import greeting.{BirthdayGreetingsFacade, Clock, FriendRepository}

import java.io.{FileWriter, PrintWriter}
import java.time.LocalDate
import scala.io.Source

class Config(val args: Array[String]) {

  private def greetingsSender() = {
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

  private def clock(): Clock = {
    val clockFile = args(1)
    new Clock {
      override def today: LocalDate = Date(Source.fromResource(clockFile).getLines().next())
    }
  }

  private def friendRepository(): FriendRepository = {
    val inputFile = args(0)
    new FriendsFile(inputFile)
  }

  val greetingsFacade = new BirthdayGreetingsFacade(friendRepository(), clock(), greetingsSender())

}
