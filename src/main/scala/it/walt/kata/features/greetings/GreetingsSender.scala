package it.walt.kata.features.greetings

trait GreetingsSender {
  def sendHappyBirthdayTo(friend: Friend): Unit
  def sendRemainderTo(friend: Friend, birthdayFriend: Friend): Unit
}
