package walt.kata
package greeting

class EmailSenderMock() extends EmailSender {

  var greetings: Seq[Greeting] = Seq.empty

  override def sendGreetingsTo(friends: Seq[Friend]): Unit = {
    friends
      .map(friend => Greeting(friend.firstName))
      .foreach(greeting => greetings = greetings :+ greeting)

    friends
      .map(friend => Email(friend.firstName, friend.emailAddress))
      .foreach(email => emails = emails :+ email)

  }

  var emails: Seq[Email] = Seq.empty
}
