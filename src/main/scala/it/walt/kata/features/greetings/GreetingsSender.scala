package it.walt.kata.features.greetings

trait GreetingsSender {
  def sendGreetingsTo(friend: Friend): Unit
}
