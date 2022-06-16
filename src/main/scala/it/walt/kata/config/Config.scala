package it.walt.kata.config

import it.walt.kata.features.date.Clock
import it.walt.kata.features.email.{EmailGateway, EmailSender}
import it.walt.kata.features.greetings.{BirthdayGreetingsFacade, FriendRepository}
import it.walt.kata.infrastructure._

class Config(val args: Array[String]) {
  lazy val greetingsFacade = new BirthdayGreetingsFacade(friendRepository, clock, greetingsSender)

  private lazy val friendRepository: FriendRepository =
    new FriendsFile(if (isAcceptanceTest) args(0) else "friends.txt")

  private lazy val clock: Clock =
    if (isAcceptanceTest) new ClockReadFromFile(args(1)) else new RealClock

  private lazy val greetingsSender = new EmailSender(emailGateway)

  private lazy val emailGateway: EmailGateway = if (isAcceptanceTest)
    new WriteEmailOnFile(args(2)) with WriteEmailOnConsole else new WriteEmailOnConsole {}

  private lazy val isAcceptanceTest = args.length == 3
  }
