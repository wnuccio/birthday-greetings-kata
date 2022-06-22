package it.walt.kata.features.email

import it.walt.kata.features.greetings.{Friend, GreetingsSender}

class EmailSender(emailGateway: EmailGateway) extends GreetingsSender {

  final override def sendHappyBirthdayTo(friend: Friend): Unit = {
    val email = new HappyBirthdayEmail(friend)
    emailGateway.sendEmail(email)
  }

  override def sendRemainderTo(friend: Friend, birthdayFriend: Friend): Unit = {
    val email = BirthdayRemainderEmail(friend, birthdayFriend)
    emailGateway.sendEmail(email)
  }

  override def sendSingleRemainderTo(otherFriend: Friend, birthdayFriends: Seq[Friend]): Unit = {
    val email = BirthdaySingleRemainderEmail(otherFriend, birthdayFriends)
    emailGateway.sendEmail(email)
  }
}
