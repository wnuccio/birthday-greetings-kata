package walt.kata
package greeting

class EmailSenderMock() extends EmailSender {
  var greetings: Seq[Greeting] = Seq.empty

  override def sendGreetingsTo(friends: Seq[Friend]): Unit = {
    friends
      .map(friend => Greeting(friend.firstName))
      .foreach(greeting => greetings = greetings :+ greeting)
  }
}
