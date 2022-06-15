package walt.kata
package greeting

class BirthdayGreetingsFacade(friendRepository: FriendRepository, greetingsSender: GreetingsSender, clock: Clock) {

  def sendGreetings(): Unit = {
    friendRepository
      .allFriends
      .filter(friend => friend.isBirthdate(clock.today))
      .foreach(friend => greetingsSender.sendGreetingsTo(friend))
  }

}
