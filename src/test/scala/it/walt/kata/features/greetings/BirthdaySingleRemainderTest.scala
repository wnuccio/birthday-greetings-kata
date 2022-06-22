package it.walt.kata.features.greetings

import it.walt.kata.features.date.Date
import it.walt.kata.features.email.{EmailGatewayMock, EmailSender}
import it.walt.kata.features.greetings.FriendForTest.{friend, friendRepository}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

/***
 * Test case list:
 * - three friends -> one remainder for both of other friends
 * - four friends -> two remainders
 *
 * Done
 */

class BirthdaySingleRemainderTest extends AnyFlatSpec with should.Matchers {

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

    birthdayGreetings.sendSingleRemainders()

    emailGateway.emailSent() shouldBe 1
    emailGateway.singleRemainderSentTo("Mary", about("John Doe"), about("Walt Nuc")) shouldBe true
  }

  ignore should "send four remainders" in {
    val clock = ClockStub.today(Date("2022/06/15"))
    val friends: Seq[Friend] = Seq(
      friend("John Doe",  "1980/06/15", "john.doe@foobar.com"),
      friend("Mary Ann",  "1985/07/16", "mary.ann@foobar.com"),
      friend("Roby Ron",  "1990/07/17", "roby.ron@foobar.com"),
      friend("Walt Nuc",  "1975/06/15", "john.doe@foobar.com"),
    )
    val emailGateway = new EmailGatewayMock()
    val emailSender = new EmailSender(emailGateway)
    val birthdayGreetings = new BirthdayGreetingsFacade(friendRepository(friends), clock, emailSender)

    birthdayGreetings.sendRemainders()

    emailGateway.emailSent() shouldBe 4
    emailGateway.singleRemainderSentTo("Mary", about("John")) shouldBe true
    emailGateway.singleRemainderSentTo("Mary", about("Walt")) shouldBe true
    emailGateway.singleRemainderSentTo("Roby", about("John")) shouldBe true
    emailGateway.singleRemainderSentTo("Roby", about("Walt")) shouldBe true
  }

  private def about(name: String): String = name
}
