package it.walt.kata.config

import it.walt.kata.features.date.Clock
import it.walt.kata.features.email.{Email, EmailGateway, EmailSender}
import it.walt.kata.features.greetings.{BirthdayGreetingsFacade, FriendRepository}
import it.walt.kata.infrastructure.{ClockReadFromFile, FriendsFile}

import java.io.{FileWriter, PrintWriter}
import java.time.LocalDate

class Config(val args: Array[String]) {
  val greetingsFacade = new BirthdayGreetingsFacade(friendRepository(), clock(), greetingsSender())

    private def friendRepository(): FriendRepository = {
      val inputFile = if (args.length == 3) args(0) else "friends.txt"
      new FriendsFile(inputFile)
    }

    private def clock(): Clock = {
      if (args.length == 3) new ClockReadFromFile(args(1))
      else new RealClock
    }

    private def greetingsSender() = {
      val emailGateway = if (args.length == 3)
        new WriteEmailOnFile(args(2)) else new WriteEmailOnConsole()

      new EmailSender(emailGateway)
    }

    class RealClock() extends Clock {
      override def today: LocalDate = LocalDate.now()
    }

    class WriteEmailOnFile(outputFile: String) extends EmailGateway {
      val writer = new PrintWriter(new FileWriter(s"src/main/resources/$outputFile"))
      override def sendEmail(email: Email): Unit = {
        writer.append(s"email to: ${email.personName}, ${email.address.value}\n")
        writer.flush()
      }
    }

    class WriteEmailOnConsole() extends EmailGateway {
      override def sendEmail(email: Email): Unit = {
        print(s"email to: ${email.personName}, ${email.address.value}\n")
      }
    }

  }
