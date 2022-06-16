package walt.kata
package friendsfile

import date.Date
import email.EmailAddress
import greeting.Friend

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class FriendsFileTest extends AnyFlatSpec with should.Matchers {

  "The friends file " should "return a list of friends read from a file" in {
    val friendsFile: FriendsFile = new FriendsFile("test/friends.txt")

    val friends: Seq[Friend] = friendsFile.allFriends

    friends.size shouldBe 3
    friends should contain (friend("John", "1982-10-08", "john.doe@foobar.com"))
    friends should contain (friend("Mary", "1975-09-11", "mary.ann@foobar.com"))
    friends should contain (friend("Walt", "1982-09-11", "walt.nuc@foobar.com"))
  }

  private def friend(john: String, birthdate: String, email: String) = {
    Friend(john, Date(birthdate), EmailAddress(email))
  }

}
