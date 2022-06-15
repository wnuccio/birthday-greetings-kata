package walt.kata
package greeting

import email.{EmailAddress, EmailSenderMock}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/***
 * Test case list:
 * - one friend -> no greeting if today is not her birthday
 * - two friend -> one greeting since it is the birthday of the first one
 *
 * Done
 * - one friend -> one greeeting
 * - one friend -> one greeting with verification of the email address
 * - one friend -> one greeting with verification of the message
 */

class BirthdayGreetingSenderTest extends AnyFlatSpec with should.Matchers {

  "The greeting sender " should "send one email to the specified address" in {
    val birthdate = parseDate("2022-06-15")
    val friends: Seq[Friend] = Seq(Friend("John", EmailAddress("john.doe@foobar.com"), birthdate))
    val emailSender = new EmailSenderMock()
    val clock = new ClockStub(birthdate)
    val birthdayGreetingsSender = new BirthdayGreetingsSender(friends, emailSender, clock)

    birthdayGreetingsSender.sendGreetings()

    emailSender.emails.size shouldBe 1
    emailSender.emails.head.address shouldBe EmailAddress("john.doe@foobar.com")
  }

  "The greeting sender " should "send one email to the specified friend" in {
    val birthdate = parseDate("2022-06-15")
    val friends: Seq[Friend] = Seq(Friend("John", EmailAddress("john.doe@foobar.com"), birthdate))
    val emailSender = new EmailSenderMock()
    val clock = new ClockStub(parseDate("2022-06-15"))
    val birthdayGreetingsSender = new BirthdayGreetingsSender(friends, emailSender, clock)

    birthdayGreetingsSender.sendGreetings()

    emailSender.emails.size shouldBe 1
    emailSender.emails.head.text shouldBe
      """
        |Subject: Happy birthday!
        |
        | Happy birthday, dear John!
        |""".stripMargin
  }

  "The greeting sender " should "send no email if today is not a birthday" in {
    val birthdate = parseDate("1975-09-07")
    val friend = Friend("John", EmailAddress("john.doe@foobar.com"), birthdate)
    val clock = new ClockStub(parseDate("2022-06-15"))
    val friends: Seq[Friend] = Seq(friend)
    val emailSender = new EmailSenderMock()
    val birthdayGreetingsSender = new BirthdayGreetingsSender(friends, emailSender, clock)

    birthdayGreetingsSender.sendGreetings()

    emailSender.emails shouldBe empty
  }


  private def parseDate(dateString: String): LocalDate = {
    LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
  }
}
