package it.walt.kata

object BirthdayGreetings {

  def main(args: Array[String]): Unit = {
    val greetingsFacade = new config.Config(args).greetingsFacade
    greetingsFacade.sendGreetings()
  }
}
