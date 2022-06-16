package it.walt.kata.config

import it.walt.kata.features.email.EmailSender
import it.walt.kata.infrastructure._

class TestConfig(val args: Array[String]) extends Config {
  override protected lazy val friendRepository = new FriendsFile(args(0))
  override protected lazy val clock = new ClockReadFromFile(args(1))
  override protected lazy val greetingsSender = new EmailSender(emailGateway)

  private lazy val emailGateway = new WriteEmailOnFile(args(2)) with WriteEmailOnConsole
  }
