package it.walt.kata.features.greetings

import it.walt.kata.features.date.Date
import it.walt.kata.features.email.{EmailAddress, EmailGatewayMock, EmailSender}
import it.walt.kata.features.greetings.FriendForTest.friend
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
    val friends: Seq[Friend] = Seq(friend("John", "2022/06/15", "john.doe@foobar.com"))
    val emailGateway = new EmailGatewayMock()
    val emailSender = new EmailSender(emailGateway)
    val clock = ClockStub.today(Date("2022/06/15"))
    val birthdayGreetings = new BirthdayGreetingsFacade(friendRepository(friends), clock, emailSender)

    birthdayGreetings.sendGreetings()

    emailGateway.emails.size shouldBe 1
    emailGateway.emails.head.address shouldBe EmailAddress("john.doe@foobar.com")
  }

  "The greeting sender " should "send one email to the specified friend" in {
    val friends: Seq[Friend] = Seq(friend("John", "2022/06/15", "john.doe@foobar.com"))
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
      friend("John", "1975/06/14", "john.doe@foobar.com"),
      friend("Alan", "1975/06/15", "alan.foe@foobar.com"),
      friend("Mike", "1975/07/15", "mike.toe@foobar.com"),
      friend("Roby", "1976/06/15", "roby.foe@foobar.com")
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

  /***
   * 2400 is a leap year, 2401 is not
   */
  "The greeting sender " should "send an email on 28th Feb for persons born on 29th and non-leap years" in {
    val clock = ClockStub.today(Date("2401/02/28"))
    val friends: Seq[Friend] = Seq(
      friend("John", "2000/02/28"),
      friend("Alan", "2000/02/29"),
    )
    val emailGateway = new EmailGatewayMock()
    val emailSender = new EmailSender(emailGateway)
    val birthdayGreetings = new BirthdayGreetingsFacade(friendRepository(friends), clock, emailSender)

    birthdayGreetings.sendGreetings()

    val mailsSentTo = emailGateway.emails.map(_.personName)
    mailsSentTo should contain("John")
    mailsSentTo should contain("Alan")
  }

  "The greeting sender " should "not send an email on 28th Feb in leap years" in {
    val clock = ClockStub.today(Date("2400/02/28"))
    val friends: Seq[Friend] = Seq(
      friend("John", "2000/02/28"),
      friend("Alan", "2000/02/29"),
    )
    val emailGateway = new EmailGatewayMock()
    val emailSender = new EmailSender(emailGateway)
    val birthdayGreetings = new BirthdayGreetingsFacade(friendRepository(friends), clock, emailSender)

    birthdayGreetings.sendGreetings()

    emailGateway.emails.size shouldBe 1
    val mailsSentTo = emailGateway.emails.map(_.personName)
    mailsSentTo should contain("John")
  }

  private def friendRepository(friendList: Seq[Friend]): FriendRepository = new FriendRepository {
      override def allFriends: Seq[Friend] = friendList
    }

}
