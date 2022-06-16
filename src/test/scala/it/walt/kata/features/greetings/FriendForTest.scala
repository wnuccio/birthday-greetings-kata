package it.walt.kata.features.greetings

import it.walt.kata.features.date.Date
import it.walt.kata.features.email.EmailAddress

object FriendForTest {
  def friend(name: String, birthdate: String, email: String): Friend = {
    Friend(name, Date(birthdate), EmailAddress(email))
  }

  def friend(name: String, birthdate: String): Friend = {
    friend(name, birthdate, "email")
  }

  def friend(birthdate: String): Friend = {
    friend("a friend", birthdate, "email")
  }

}
