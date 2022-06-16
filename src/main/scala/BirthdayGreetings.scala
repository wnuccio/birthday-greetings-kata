package walt.kata

import date.Date
import email.{Email, EmailGateway, EmailSender}
import friendsfile.FriendsFile
import greeting.{BirthdayGreetingsFacade, Clock}

import java.io.{FileWriter, PrintWriter}
import java.time.LocalDate
import scala.io.Source

object BirthdayGreetings {

  def main(args: Array[String]): Unit = {
    val inputFile = args(0)
    val clockFile = args(1)
    val outputFile = args(2)

    val friendRepository = new FriendsFile(inputFile)

    val clockReader = new Clock {
      override def today: LocalDate = Date(Source.fromResource(clockFile).getLines().next())
    }

    val emailSender = new EmailSender(new EmailGateway {
      val writer = new PrintWriter(new FileWriter(s"src/main/resources/$outputFile"))

      override def sendEmail(email: Email): Unit = {
        writer.append(s"email to: ${email.personName}, ${email.address.value}\n")
        writer.flush()
      }
    })

    new BirthdayGreetingsFacade(friendRepository, emailSender, clockReader).sendGreetings()
  }
}
