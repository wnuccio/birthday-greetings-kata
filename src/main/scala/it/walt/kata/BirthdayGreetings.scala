package it.walt.kata

import it.walt.kata.config.{RealConfig, TestConfig}

object BirthdayGreetings {

  def main(args: Array[String]): Unit = {
    val config = if (args.length != 3) new RealConfig() else new TestConfig(args)

    val greetingsFacade = config.greetingsFacade
    greetingsFacade.sendHappyBirthdays()
  }
}
