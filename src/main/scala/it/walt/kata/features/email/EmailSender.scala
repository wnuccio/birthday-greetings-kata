package it.walt.kata.features.email

import it.walt.kata.features.greetings.{Friend, GreetingsSender}

class EmailSender(emailGateway: EmailGateway) extends GreetingsSender {

  final override def sendGreetingsTo(friend: Friend): Unit = {
    val email = Email.happyBirthdayTo(friend)
    emailGateway.sendEmail(email)
  }

  override def sendRemainderTo(friend: Friend, birthdayFriend: Friend): Unit = {
    val email = Email.remainder(friend, birthdayFriend: Friend)
    emailGateway.sendEmail(email)
  }
}
