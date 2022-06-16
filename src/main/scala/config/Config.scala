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
  val greetingsFacade = new BirthdayGreetingsFacade(friendRepository(), clock(), greetingsSender())

  private def friendRepository(): FriendRepository = {
    val inputFile = if (args.length == 3) args(0) else "friends.txt"
    new FriendsFile(inputFile)
  }

  private def clock(): Clock = {
    if (args.length == 3) new ReadTodayFromFile(args(1))
    else new RealClock
  }

  private def greetingsSender() = {
    val emailGateway = if (args.length == 3)
      new WriteEmailOnFile(args(2)) else new WriteEmailOnConsole()

    new EmailSender(emailGateway)
  }

  private class ReadTodayFromFile(clockFile: String) extends Clock {
    override def today: LocalDate = Date(Source.fromResource(clockFile).getLines().next())
  }

  private class RealClock() extends Clock {
    override def today: LocalDate = LocalDate.now()
  }

  private class WriteEmailOnFile(outputFile: String) extends EmailGateway {
    val writer = new PrintWriter(new FileWriter(s"src/main/resources/$outputFile"))
    override def sendEmail(email: Email): Unit = {
        writer.append(s"email to: ${email.personName}, ${email.address.value}\n")
        writer.flush()
      }
    }

  private class WriteEmailOnConsole() extends EmailGateway {
    override def sendEmail(email: Email): Unit = {
      print(s"email to: ${email.personName}, ${email.address.value}\n")
      }
    }
}
