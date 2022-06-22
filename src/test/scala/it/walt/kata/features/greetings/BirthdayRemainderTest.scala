package it.walt.kata.features.greetings

import it.walt.kata.features.date.Date
import it.walt.kata.features.email.{EmailGatewayMock, EmailSender}
import it.walt.kata.features.greetings.FriendForTest.{friend, friendRepository}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

/***
 * Test case list:
 * - four friends -> four remainders
 *
 * Done
 * - two friends -> one remainder
 * - three friends -> two remainders, one for both of other friends
 */

class BirthdayRemainderTest extends AnyFlatSpec with should.Matchers {

  "The greeting sender" should "send one remainder" in {
    val clock = ClockStub.today(Date("2022/06/15"))
    val friends: Seq[Friend] = Seq(
      friend("John Doe",  "1975/06/15", "john.doe@foobar.com"),
      friend("Mary Ann",  "1975/07/16", "mary.ann@foobar.com")
    )
    val emailGateway = new EmailGatewayMock()
    val emailSender = new EmailSender(emailGateway)
    val birthdayGreetings = new BirthdayGreetingsFacade(friendRepository(friends), clock, emailSender)

    birthdayGreetings.sendRemainders()

    emailGateway.sentEmailToAddress("mary.ann@foobar.com") shouldBe true
    emailGateway.sentEmailWithText(
      """
        |Subject: Birthday Remainder!
        |
        | Dear Mary!
        |
        | Today is John Doe's birthday.
        | Don't forget to send him a message !
        |""".stripMargin) shouldBe true
  }

  "The greeting sender" should "send two remainders" in {
    val clock = ClockStub.today(Date("2022/06/15"))
    val friends: Seq[Friend] = Seq(
    friend("John Doe",  "1980/06/15", "john.doe@foobar.com"),
    friend("Mary Ann",  "1985/07/16", "mary.ann@foobar.com"),
    friend("Walt Nuc",  "1975/06/15", "john.doe@foobar.com"),
    )
    val emailGateway = new EmailGatewayMock()
    val emailSender = new EmailSender(emailGateway)
    val birthdayGreetings = new BirthdayGreetingsFacade(friendRepository(friends), clock, emailSender)

    birthdayGreetings.sendRemainders()

    emailGateway.sentRemaindersTo("Mary", about("John", "Walt")) shouldBe true
  }

  private def about(names: String*): Seq[String] = names
}
