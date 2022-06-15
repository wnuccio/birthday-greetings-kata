package walt.kata
package email

import greeting.{Friend, GreetingsSender}

class EmailSender(emailGateway: EmailGateway) extends GreetingsSender {

  final override def sendGreetingsTo(friend: Friend): Unit = {
    val email = Email(friend.firstName, friend.emailAddress)
    emailGateway.sendEmail(email)
  }
}
