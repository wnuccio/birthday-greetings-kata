package it.walt.kata.features.email

import it.walt.kata.features.greetings.Friend


object Email {
  def remainder(friend: Friend, birthdayFriend: Friend): Email = {
    val textTemplate: String =
    """
      |Subject: Birthday Remainder!
      |
      | Dear <first_name>!
      |
      | Today is John Doe's birthday.
      | Don't forget to send him a message !
      |""".stripMargin

    val text = textTemplate.replace("<first_name>", friend.firstName)
    new Email(friend.firstName, friend.emailAddress, text)
  }

  def apply(firstName: String, address: EmailAddress): Email = {
    val textTemplate: String =
    """
      |Subject: Happy birthday!
      |
      | Happy birthday, dear <first_name>!
      |""".stripMargin

    val text: String = textTemplate.replace("<first_name>", firstName)
    new Email(firstName, address, text)
  }
}

class Email(val sentTo: String, val address: EmailAddress, val text: String) {
}


