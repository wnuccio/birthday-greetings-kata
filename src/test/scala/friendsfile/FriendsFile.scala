package walt.kata
package friendsfile

import email.EmailAddress
import greeting.{Friend, FriendRepository}

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.io.Source

class FriendsFile(filename: String) extends FriendRepository{

  override def allFriends: Seq[Friend] = {
    val lines: Iterator[String] = Source.fromResource(filename).getLines()
    lines.next() // discard header line
    lines.map(toFriend).toSeq
  }

  private def toFriend(line: String): Friend = {
    val fields: Array[String] = line.split(", ")
    val firstName = fields(1)
    val birthdateString = fields(2)
    val emailAddressString = fields(3)

    val birthdate = LocalDate.parse(birthdateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
    val emailAddress = EmailAddress(emailAddressString)

    Friend(firstName, birthdate, emailAddress)
  }
}
