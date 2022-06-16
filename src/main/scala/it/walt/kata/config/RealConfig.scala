package it.walt.kata.config

import it.walt.kata.features.email.{EmailGateway, EmailSender}
import it.walt.kata.infrastructure._

class RealConfig() extends Config {
  override protected lazy val friendRepository = new FriendsFile("friends.txt")
  override protected lazy val clock = new RealClock
  override protected lazy val greetingsSender = new EmailSender(emailGateway)

  private lazy val emailGateway: EmailGateway = new WriteEmailOnConsole {}
  }
