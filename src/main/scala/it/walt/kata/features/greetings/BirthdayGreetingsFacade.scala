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

  def sendSingleRemainders(): Unit = {
    for(otherFriend <- otherFriends)
      greetingsSender.sendSingleRemainderTo(otherFriend, birthdayFriends)
  }

  private lazy val birthdayFriends: Seq[Friend] =
    friendRepository
      .allFriends
      .filter(_.isBirthdate(clock.today))

  private lazy val otherFriends =
    friendRepository
      .allFriends
      .filterNot(_.isBirthdate(clock.today))

}
