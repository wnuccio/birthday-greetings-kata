package it.walt.kata.features.email

import it.walt.kata.features.greetings.{Friend, GreetingsSender}

class EmailSender(emailGateway: EmailGateway) extends GreetingsSender {

  final override def sendGreetingsTo(friend: Friend): Unit = {
    val email = Email(friend.firstName, friend.emailAddress)
    emailGateway.sendEmail(email)
  }
}
