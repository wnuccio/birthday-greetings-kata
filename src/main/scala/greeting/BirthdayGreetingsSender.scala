package walt.kata
package greeting

class BirthdayGreetingsSender(friends: Seq[Friend], greetingsSender: GreetingsSender) {
  def sendGreetings(): Unit = {
    friends.foreach(friend => greetingsSender.sendGreetingsTo(friend))
  }

}
