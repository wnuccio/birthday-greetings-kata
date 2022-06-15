package walt.kata
package email

import greeting.{Friend, GreetingsSender}

class EmailSender() extends GreetingsSender {

  final override def sendGreetingsTo(friend: Friend): Unit = {
    val email = Email(friend.firstName, friend.emailAddress)
    sendEmail(email)
  }

  protected def sendEmail(email: Email): Unit = {
    // some real logic to send an e-mail
  }
}
