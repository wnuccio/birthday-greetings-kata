package it.walt.kata.features.email

import it.walt.kata.features.greetings.Friend


object Email {
  def remainder(toFriend: Friend, birthdayFriend: Friend): Email = {
    new BirthdayRemainderEmail(toFriend, birthdayFriend)
  }

  def happyBirthdayTo(firstName: String, address: EmailAddress): Email = {
    new HappyBirthdayEmail(firstName, address)
  }
}

trait Email {
  def typetext: String
  def sentTo: String
  def address: EmailAddress
  def text: String
}

class HappyBirthdayEmail(val sentTo: String, val address: EmailAddress) extends Email {

  private lazy val textTemplate: String =
    """
      |Subject: Happy birthday!
      |
      | Happy birthday, dear <first_name>!
      |""".stripMargin

  override val typetext: String = "happy birthday"

  override val text: String = textTemplate.replace("<first_name>", sentTo)
}

class BirthdayRemainderEmail(toFriend: Friend, birthdayFriend: Friend) extends Email {

  private lazy val textTemplate: String =
    """
      |Subject: Birthday Remainder!
      |
      | Dear <first_name>!
      |
      | Today is <other_friend>'s birthday.
      | Don't forget to send him a message !
      |""".stripMargin

  override val typetext: String = "birthday remainder "
  override val sentTo: String = toFriend.firstName
  override val address: EmailAddress = toFriend.emailAddress

  override val text: String = textTemplate
    .replace("<first_name>", sentTo)
    .replace("<other_friend>", s"${birthdayFriend.firstName} ${birthdayFriend.lastName}")

}


