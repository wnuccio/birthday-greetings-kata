package walt.kata
package features.email

import features.greetings.{Friend, GreetingsSender}

class EmailSender(emailGateway: EmailGateway) extends GreetingsSender {

  final override def sendGreetingsTo(friend: Friend): Unit = {
    val email = Email(friend.firstName, friend.emailAddress)
    emailGateway.sendEmail(email)
  }
}