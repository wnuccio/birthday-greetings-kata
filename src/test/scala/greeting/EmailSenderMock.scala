package walt.kata
package greeting

class EmailSenderMock() extends EmailSender {

  override def sendGreetingsTo(friends: Seq[Friend]): Unit = {
    friends
      .map(friend => Email(friend.firstName, friend.emailAddress))
      .foreach(email => emails = emails :+ email)
  }

  var emails: Seq[Email] = Seq.empty
}
