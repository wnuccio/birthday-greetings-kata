package it.walt.kata.features.greetings

import it.walt.kata.features.email.EmailGatewayMock
import it.walt.kata.features.greetings.BirthdayGreetingsFacadeFactory.createGreetingsFacade
import it.walt.kata.features.greetings.FriendForTest.friend
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class RemainderTest extends AnyFlatSpec with should.Matchers {

  "The greeting sender" should "send one remainder" in {
    val today = "2022/06/15"
    val friends: Seq[Friend] = Seq(
      friend("John Doe",  "1975/06/15", "john.doe@foobar.com"),
      friend("Mary Ann",  "1975/07/16", "mary.ann@foobar.com")
    )
    val emailGateway = new EmailGatewayMock()
    val birthdayGreetings = createGreetingsFacade(today, friends, emailGateway)
    birthdayGreetings.sendRemainders()

    emailGateway.emailSentToAddress("mary.ann@foobar.com") shouldBe true
    emailGateway.emailSentWithText(
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
    val today = "2022/06/15"
    val friends: Seq[Friend] = Seq(
      friend("John Doe",  "1980/06/15", "john.doe@foobar.com"),
      friend("Mary Ann",  "1985/07/16", "mary.ann@foobar.com"),
      friend("Walt Nuc",  "1975/06/15", "john.doe@foobar.com"),
    )
    val emailGateway = new EmailGatewayMock()
    val birthdayGreetings = createGreetingsFacade(today, friends, emailGateway)
    birthdayGreetings.sendRemainders()

    emailGateway.emailSent() shouldBe 2
    emailGateway.remainderSentTo("Mary", about("John")) shouldBe true
    emailGateway.remainderSentTo("Mary", about("Walt")) shouldBe true
  }

  "The greeting sender" should "send four remainders" in {
    val today = "2022/06/15"
    val friends: Seq[Friend] = Seq(
      friend("John Doe",  "1980/06/15", "john.doe@foobar.com"),
      friend("Mary Ann",  "1985/07/16", "mary.ann@foobar.com"),
      friend("Roby Ron",  "1990/07/17", "roby.ron@foobar.com"),
      friend("Walt Nuc",  "1975/06/15", "john.doe@foobar.com"),
    )
    val emailGateway = new EmailGatewayMock()
    val birthdayGreetings = createGreetingsFacade(today, friends, emailGateway)
    birthdayGreetings.sendRemainders()

    emailGateway.emailSent() shouldBe 4
    emailGateway.remainderSentTo("Mary", about("John")) shouldBe true
    emailGateway.remainderSentTo("Mary", about("Walt")) shouldBe true
    emailGateway.remainderSentTo("Roby", about("John")) shouldBe true
    emailGateway.remainderSentTo("Roby", about("Walt")) shouldBe true
  }

  private def about(birthdayFriendName: String): String = birthdayFriendName
}
