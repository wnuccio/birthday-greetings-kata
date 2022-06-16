package walt.kata
package features.greeting

import features.date.Clock

class BirthdayGreetingsFacade(friendRepository: FriendRepository, clock: Clock, greetingsSender: GreetingsSender) {

  def sendGreetings(): Unit = {
    friendRepository
      .allFriends
      .filter(friend => friend.isBirthdate(clock.today))
      .foreach(friend => greetingsSender.sendGreetingsTo(friend))
  }

}
