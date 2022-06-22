package it.walt.kata.features.greetings

import it.walt.kata.features.date.Clock

class BirthdayGreetingsFacade(friendRepository: FriendRepository, clock: Clock, greetingsSender: GreetingsSender) {

  def sendHappyBirthdays(): Unit =
    for (friend <- birthdayFriends)
      yield greetingsSender.sendHappyBirthdayTo(friend)

  def sendRemainders(): Unit =
    for (
      otherFriend <- otherFriends;
      birthdayFriend <- birthdayFriends
    )
    yield greetingsSender.sendRemainderTo(otherFriend, birthdayFriend)

  private lazy val birthdayFriends: Seq[Friend] =
    for (friend <- friendRepository.allFriends if friend.isBirthdate(clock.today))
      yield friend

  private lazy val otherFriends =
    for (friend <- friendRepository.allFriends if ! friend.isBirthdate(clock.today))
      yield friend
}
