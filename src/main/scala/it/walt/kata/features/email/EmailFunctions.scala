package it.walt.kata.features.email

object EmailFunctions {
  def toString(email: Email): String =
    email match {
      case HappyBirthdayEmail(_)                            =>
        s"happy birthday to: ${email.sentTo}, ${email.address.value}"
      case BirthdayRemainderEmail(_, birthdayFriend)        =>
        s"remainder to: ${email.sentTo} for birthday of: ${birthdayFriend.firstName}"
      case BirthdaySingleRemainderEmail(_, birthdayFriends) =>
        s"single remainder to: ${email.sentTo} for birthdays of: ${birthdayFriends.map(_.firstName)}"
    }
}
