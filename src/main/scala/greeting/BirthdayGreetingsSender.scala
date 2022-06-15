package walt.kata
package greeting

class BirthdayGreetingsSender(friends: Seq[Friend], greetingsSender: GreetingsSender, clock: Clock) {
  def sendGreetings(): Unit = {
    friends
      .filter(friend => friend.isBirthdate(clock.today))
      .foreach(friend => greetingsSender.sendGreetingsTo(friend))
  }

}
