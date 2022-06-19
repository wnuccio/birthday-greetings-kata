package it.walt.kata.features.email

import it.walt.kata.features.greetings.Friend


object Email {
  def remainder(friend: Friend, birthdayFriend: Friend): Email = {
    new BirthdayRemainderEmail(friend.firstName, friend.emailAddress)
  }

  def happyBirthdayTo(firstName: String, address: EmailAddress): Email = {
    new HappyBirthdayEmail(firstName, address)
  }
}

class Email(val sentTo: String, val address: EmailAddress, val text: String) {
}

class HappyBirthdayEmail(sentTo: String, address: EmailAddress) extends
  Email(sentTo, address, null) {

  private lazy val textTemplate: String =
    """
      |Subject: Happy birthday!
      |
      | Happy birthday, dear <first_name>!
      |""".stripMargin

  override val text: String = textTemplate.replace("<first_name>", sentTo)
}

class BirthdayRemainderEmail(sentTo: String, address: EmailAddress) extends
  Email(sentTo, address, null) {

  private lazy val textTemplate: String =
    """
      |Subject: Birthday Remainder!
      |
      | Dear <first_name>!
      |
      | Today is John Doe's birthday.
      | Don't forget to send him a message !
      |""".stripMargin

  override val text: String = textTemplate.replace("<first_name>", sentTo)
}


