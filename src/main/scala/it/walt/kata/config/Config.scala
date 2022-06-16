package it.walt.kata.config

import it.walt.kata.features.date.Clock
import it.walt.kata.features.email.EmailSender
import it.walt.kata.features.greetings.{BirthdayGreetingsFacade, FriendRepository}
import it.walt.kata.infrastructure._

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
  }
