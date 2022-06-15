package walt.kata
package greeting

trait EmailSender {
  def sendGreetingsTo(friends: Seq[Friend]): Unit

}
