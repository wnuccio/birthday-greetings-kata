package it.walt.kata.features.greetings

import it.walt.kata.features.date.Clock

class BirthdayGreetingsFacade(friendRepository: FriendRepository, clock: Clock, greetingsSender: GreetingsSender) {

  def sendGreetings(): Unit = {
    friendRepository
      .allFriends
      .filter(friend => friend.isBirthdate(clock.today))
      .foreach(friend => greetingsSender.sendGreetingsTo(friend))
  }

  def sendRemainders(): Unit = {
    val birthdays = friendRepository
      .allFriends
      .filter(friend => friend.isBirthdate(clock.today))

    friendRepository
      .allFriends
      .filter(friend => ! birthdays.contains(friend))
      .foreach(friend => {
        birthdays.foreach(birthdayFriend => {
          greetingsSender.sendRemainderTo(friend, birthdayFriend)
        })
      })

  }
}
