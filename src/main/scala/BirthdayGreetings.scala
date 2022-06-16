package walt.kata

import config.Config

object BirthdayGreetings {

  def main(args: Array[String]): Unit = {
    val greetingsFacade = new Config(args).greetingsFacade
    greetingsFacade.sendGreetings()
  }
}
