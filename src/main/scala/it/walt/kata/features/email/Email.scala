package it.walt.kata.features.email

import it.walt.kata.features.greetings.Friend

sealed trait Email {
  def sentTo: String
  def address: EmailAddress
  def text: String
}

case class HappyBirthdayEmail(toFriend: Friend) extends Email {
  private lazy val textTemplate: String =
    """
      |Subject: Happy birthday!
      |
      | Happy birthday, dear <first_name>!
      |""".stripMargin

  override val sentTo: String        = toFriend.firstName
  override val address: EmailAddress = toFriend.emailAddress
  override val text: String          = textTemplate.replace("<first_name>", sentTo)
}

case class BirthdayRemainderEmail(toFriend: Friend, birthdayFriend: Friend) extends Email {
  private lazy val textTemplate: String =
    """
      |Subject: Birthday Remainder!
      |
      | Dear <first_name>!
      |
      | Today is <other_friend>'s birthday.
      | Don't forget to send him a message !
      |""".stripMargin

  override val sentTo: String        = toFriend.firstName
  override val address: EmailAddress = toFriend.emailAddress
  override val text: String          = textTemplate
    .replace("<first_name>", sentTo)
    .replace("<other_friend>", s"${birthdayFriend.firstName} ${birthdayFriend.lastName}")
}

case class BirthdaySingleRemainderEmail(toFriend: Friend, birthdayFriends: Seq[Friend]) extends Email {
  private lazy val textTemplate: String =
    """
      |Subject: Birthday Remainder!
      |
      | Dear <first_name>,
      |
      | Today is <other_friends_full_names>'s birthday.
      | Don't forget to send him a message !
      |""".stripMargin

  override val sentTo: String        = toFriend.firstName
  override val address: EmailAddress = toFriend.emailAddress
  override lazy val text: String = {
    def otherFriendFullNames(): String = {
      if (birthdayFriends.size == 1) return birthdayFriends.head.fullName
      val allButLast = birthdayFriends.map(_.fullName).init
      val result     = allButLast.mkString(", ") + " and " + birthdayFriends.last.fullName
      println(result)
      result
    }

    val result = textTemplate
      .replace("<first_name>", sentTo)
      .replace("<other_friends_full_names>", otherFriendFullNames())

    println(result)
    result
  }
}
