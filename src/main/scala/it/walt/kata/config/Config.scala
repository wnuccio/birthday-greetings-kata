package it.walt.kata.config

import it.walt.kata.features.date.Clock
import it.walt.kata.features.greetings.{BirthdayGreetingsFacade, FriendRepository, GreetingsSender}

trait Config {
  lazy val greetingsFacade = new BirthdayGreetingsFacade(friendRepository, clock, greetingsSender)

  protected val friendRepository: FriendRepository
  protected val clock: Clock
  protected val greetingsSender: GreetingsSender
  }
