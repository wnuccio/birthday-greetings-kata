package it.walt.kata.features.greetings

import it.walt.kata.features.email.EmailGatewayMock
import it.walt.kata.features.greetings.BirthdayGreetingsFacadeFactory.createGreetingsFacade
import it.walt.kata.features.greetings.FriendForTest.friend
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class HappyBirthdayTest extends AnyFlatSpec with should.Matchers {

  "The greeting sender " should "send one happy birthday to the specified address" in {
    val today = "2022/06/15"
    val friends: Seq[Friend] = Seq(
      friend("John", "2022/06/15", "john.doe@foobar.com")
    )
    val emailGateway = new EmailGatewayMock()
    val birthdayGreetings = createGreetingsFacade(today, friends, emailGateway)

    birthdayGreetings.sendHappyBirthdays()

    emailGateway.emailSentToAddress("john.doe@foobar.com") shouldBe true
  }

  "The greeting sender " should "send one happy birthday to the specified friend" in {
    val today = "2022/06/15"
    val friends: Seq[Friend] = Seq(
      friend("John", "2022/06/15", "john.doe@foobar.com"))
    val emailGateway = new EmailGatewayMock()
    val birthdayGreetings = createGreetingsFacade(today, friends, emailGateway)
    birthdayGreetings.sendHappyBirthdays()

    emailGateway.emailSentWithText(
      """
        |Subject: Happy birthday!
        |
        | Happy birthday, dear John!
        |""".stripMargin
    ) shouldBe true
  }

  "The greeting sender " should "send no happy birthday if today is not a birthday" in {
    val today = "2022/06/15"
    val friends: Seq[Friend] = Seq(
      friend("John", "1975/06/14", "john.doe@foobar.com"),
      friend("Alan", "1975/06/15", "alan.foe@foobar.com"),
      friend("Mike", "1975/07/15", "mike.toe@foobar.com"),
      friend("Roby", "1976/06/15", "roby.foe@foobar.com")
    )
    val emailGateway = new EmailGatewayMock()
    val birthdayGreetings = createGreetingsFacade(today, friends, emailGateway)

    birthdayGreetings.sendHappyBirthdays()

    emailGateway.emailSentTo("Alan", "Roby") shouldBe true
  }

  /***
   * 2400 is a leap year, 2401 is not
   */
  "The greeting sender " should "send a happy birthday on 28th Feb for persons born on 29th and non-leap years" in {
    val today = "2401/02/28"
    val friends: Seq[Friend] = Seq(
      friend("John", "2000/02/28"),
      friend("Alan", "2000/02/29"),
    )
    val emailGateway = new EmailGatewayMock()
    val birthdayGreetings = createGreetingsFacade(today, friends, emailGateway)

    birthdayGreetings.sendHappyBirthdays()

    emailGateway.emailSentTo("Alan", "John") shouldBe true
  }

  "The greeting sender " should "not send a happy birthday on 28th Feb in leap years" in {
    val today = "2400/02/28"
    val friends: Seq[Friend] = Seq(
      friend("John", "2000/02/28"),
      friend("Alan", "2000/02/29"),
    )
    val emailGateway = new EmailGatewayMock()
    val birthdayGreetings = createGreetingsFacade(today, friends, emailGateway)

    birthdayGreetings.sendHappyBirthdays()

    emailGateway.emailSentTo("John") shouldBe true
  }
}
