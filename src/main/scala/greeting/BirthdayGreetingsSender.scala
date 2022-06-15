package walt.kata
package greeting

class BirthdayGreetingsSender(friends: Seq[Friend], emailSender: GreetingsSender) {
  def sendGreetings(): Unit = {
    friends.foreach(friend => emailSender.sendGreetingsTo(friend))
  }

}
