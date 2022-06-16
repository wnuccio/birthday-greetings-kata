package walt.kata
package greeting

import date.Date
import email.{EmailAddress, EmailGatewayMock, EmailSender}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

/***
 * Test case list:
 *
 * Done
 * - one friend -> one greeeting
 * - one friend -> one greeting with verification of the email address
 * - one friend -> one greeting with verification of the message
 * - one friend -> no greeting if today is not her birthday
 * - two friend -> one greeting since it is the birthday of the first one
 */

class BirthdayGreetingFacadeTest extends AnyFlatSpec with should.Matchers {

  "The greeting sender " should "send one email to the specified address" in {
    val friends: Seq[Friend] = Seq(Friend("John", Date("2022/06/15"), EmailAddress("john.doe@foobar.com")))
    val emailGateway = new EmailGatewayMock()
    val emailSender = new EmailSender(emailGateway)
    val clock = ClockStub.today(Date("2022/06/15"))
    val birthdayGreetings = new BirthdayGreetingsFacade(friendRepository(friends), clock, emailSender)

    birthdayGreetings.sendGreetings()

    emailGateway.emails.size shouldBe 1
    emailGateway.emails.head.address shouldBe EmailAddress("john.doe@foobar.com")
  }

  "The greeting sender " should "send one email to the specified friend" in {
    val friends: Seq[Friend] = Seq(Friend("John", Date("2022/06/15"), EmailAddress("john.doe@foobar.com")))
    val emailGateway = new EmailGatewayMock()
    val emailSender = new EmailSender(emailGateway)
    val clock = ClockStub.today(Date("2022/06/15"))
    val birthdayGreetings = new BirthdayGreetingsFacade(friendRepository(friends), clock, emailSender)

    birthdayGreetings.sendGreetings()

    emailGateway.emails.size shouldBe 1
    emailGateway.emails.head.text shouldBe
      """
        |Subject: Happy birthday!
        |
        | Happy birthday, dear John!
        |""".stripMargin
  }

  "The greeting sender " should "send no email if today is not a birthday" in {
    val friends: Seq[Friend] = Seq(
      Friend("John", Date("1975/06/14"), EmailAddress("john.doe@foobar.com")),
      Friend("Alan", Date("1975/06/15"), EmailAddress("alan.foe@foobar.com")),
      Friend("Mike", Date("1975/07/15"), EmailAddress("mike.toe@foobar.com")),
      Friend("Roby", Date("1976/06/15"), EmailAddress("roby.foe@foobar.com"))
    )
    val emailGateway = new EmailGatewayMock()
    val emailSender = new EmailSender(emailGateway)
    val clock = ClockStub.today(Date("2022/06/15"))
    val birthdayGreetings = new BirthdayGreetingsFacade(friendRepository(friends), clock, emailSender)

    birthdayGreetings.sendGreetings()

    emailGateway.emails.size shouldBe 2
    val mailsSentTo = emailGateway.emails.map(_.personName)
    mailsSentTo should contain("Alan")
    mailsSentTo should contain("Roby")
  }

  private def friendRepository(friendList: Seq[Friend]): FriendRepository = new FriendRepository {
      override def allFriends: Seq[Friend] = friendList
    }

}
