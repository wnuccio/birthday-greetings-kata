package it.walt.kata.features.greetings.helpers

import it.walt.kata.features.date.Date
import it.walt.kata.features.email.EmailAddress
import it.walt.kata.features.greetings.{Friend, FriendRepository}

object FriendFactory {
  def friend(name: String, birthdate: String, email: String): Friend = {
    val nameSplit = name.split(' ')
    val firstName = nameSplit(0)
    val lastName = if (nameSplit.length > 1)  nameSplit(1) else ""
    Friend(firstName, lastName, Date(birthdate), EmailAddress(email))
  }

  def friend(name: String, birthdate: String): Friend = {
    friend(name, birthdate, "email")
  }

  def friend(birthdate: String): Friend = {
    friend("a friend", birthdate, "email")
  }

  def friendRepository(friendList: Seq[Friend]): FriendRepository = new FriendRepository {
    override def allFriends: Seq[Friend] = friendList
  }

}
