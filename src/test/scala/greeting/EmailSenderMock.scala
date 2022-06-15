package walt.kata
package greeting

class EmailSenderMock() extends EmailSender {
  var emails: Seq[Email] = Seq.empty

  override def sendGreetingsTo(friend: Friend): Unit = {
    val email = Email(friend.firstName, friend.emailAddress)
    emails = emails :+ email
  }

}
