package it.walt.kata.infrastructure

import it.walt.kata.features.date.Date
import it.walt.kata.features.email.EmailAddress
import it.walt.kata.features.greetings.{Friend, FriendRepository}

import scala.io.Source

class FriendsFile(filename: String) extends FriendRepository{

  override def allFriends: Seq[Friend] = {
    val source = Source.fromResource(filename)
    val lines = source.getLines()
    lines.next() // discard header line
    val friends = lines.map(toFriend).toSeq
    source.close()
    friends
  }

  private def toFriend(line: String): Friend = {
    val fields: Array[String] = line.split(", ")
    val firstName = fields(1)
    val birthdateString = fields(2)
    val emailAddressString = fields(3)

    val birthdate = Date(birthdateString)
    val emailAddress = EmailAddress(emailAddressString)

    Friend(firstName, birthdate, emailAddress)
  }
}
