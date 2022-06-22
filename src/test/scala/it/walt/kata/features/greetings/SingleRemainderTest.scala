package it.walt.kata.features.greetings

import it.walt.kata.features.date.Date
import it.walt.kata.features.email.EmailGatewayMock
import it.walt.kata.features.greetings.BirthdayGreetingsFacadeFactory.createGreetingsFacade
import it.walt.kata.features.greetings.FriendForTest.friend
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class SingleRemainderTest extends AnyFlatSpec with should.Matchers {

  "The greeting sender" should "send one remainder for one birthdays" in {
    val today = "2022/06/15"
    val friends = Seq(
      friend("John Doe",  "1980/06/15"),
      friend("Mary Ann",  "1985/07/16"),
    )
    val emailGateway = new EmailGatewayMock()
    val birthdayGreetings = createGreetingsFacade(today, friends, emailGateway)

    birthdayGreetings.sendSingleRemainders()

    emailGateway.emailSent() shouldBe 1
    emailGateway.singleRemainderSentTo("Mary", about("John Doe")) shouldBe true
    emailGateway.emailSentWithText(
      """
        |Subject: Birthday Remainder!
        |
        | Dear Mary,
        |
        | Today is John Doe's birthday.
        | Don't forget to send him a message !
        |""".stripMargin) shouldBe true
  }

  "The greeting sender" should "send one remainder for two birthdays" in {
    val today = "2022/06/15"
    val friends = Seq(
      friend("John Doe",  "1980/06/15"),
      friend("Mary Ann",  "1985/07/16"),
      friend("Walt Nuc",  "1975/06/15"),
    )
    val emailGateway = new EmailGatewayMock()
    val birthdayGreetings = createGreetingsFacade(today, friends, emailGateway)

    birthdayGreetings.sendSingleRemainders()

    emailGateway.emailSent() shouldBe 1
    emailGateway.singleRemainderSentTo("Mary", about("John Doe"), about("Walt Nuc")) shouldBe true
    emailGateway.emailSentWithText(
      """
        |Subject: Birthday Remainder!
        |
        | Dear Mary,
        |
        | Today is John Doe and Walt Nuc's birthday.
        | Don't forget to send him a message !
        |""".stripMargin) shouldBe true
  }

  "The greeting sender" should "send one remainder for three birthdays" in {
    val today = "2022/06/15"
    val friends: Seq[Friend] = Seq(
      friend("Mary Ann",  "1985/07/16"),
      friend("John Doe",  "1980/06/15"),
      friend("Walt Nuc",  "1975/06/15"),
      friend("Roby Ron",  "1981/06/15"),
    )
    val emailGateway = new EmailGatewayMock()
    val birthdayGreetings = createGreetingsFacade(today, friends, emailGateway)
    birthdayGreetings.sendSingleRemainders()

    emailGateway.emailSent() shouldBe 1
    emailGateway.singleRemainderSentTo("Mary",
      about("John Doe"), about("Walt Nuc"), about("Roby Ron")) shouldBe true
    emailGateway.emailSentWithText(
      """
        |Subject: Birthday Remainder!
        |
        | Dear Mary,
        |
        | Today is John Doe, Walt Nuc and Roby Ron's birthday.
        | Don't forget to send him a message !
        |""".stripMargin) shouldBe true
  }

  "The greeting sender" should "send four remainders" in {
    val today = "2022/06/15"
    val clock = ClockStub.today(Date("2022/06/15"))
    val friends: Seq[Friend] = Seq(
      friend("John Doe",  "1980/06/15"),
      friend("Mary Ann",  "1985/07/16"),
      friend("Roby Ron",  "1990/07/17"),
      friend("Walt Nuc",  "1975/06/15"),
    )
    val emailGateway = new EmailGatewayMock()

    val birthdayGreetings = createGreetingsFacade(today, friends, emailGateway)
    birthdayGreetings.sendSingleRemainders()

    emailGateway.emailSent() shouldBe 2
    emailGateway.singleRemainderSentTo("Mary", about("John Doe"), about("Walt Nuc")) shouldBe true
    emailGateway.singleRemainderSentTo("Roby", about("John Doe"), about("Walt Nuc")) shouldBe true
  }

  private def about(name: String): String = name
}
