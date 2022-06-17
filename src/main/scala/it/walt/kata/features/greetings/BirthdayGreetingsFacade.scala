package it.walt.kata.features.greetings

import it.walt.kata.features.date.Clock

class BirthdayGreetingsFacade(friendRepository: FriendRepository, clock: Clock, greetingsSender: GreetingsSender) {

  def sendGreetings(): Unit = {
    birthdayFriends
      .foreach(friend => greetingsSender.sendGreetingsTo(friend))
  }

  def sendRemainders(): Unit = {
    otherFriends
      .foreach(friend => {
        birthdayFriends.foreach(birthdayFriend => {
          greetingsSender.sendRemainderTo(friend, birthdayFriend)
        })
      })

  }

  private lazy val birthdayFriends: Seq[Friend] = friendRepository
      .allFriends
      .filter(friend => friend.isBirthdate(clock.today))

  private lazy val otherFriends = friendRepository
      .allFriends
      .filter(friend => !birthdayFriends.contains(friend))
}
