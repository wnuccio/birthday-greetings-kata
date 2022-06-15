package walt.kata
package greeting

import email.{EmailAddress, EmailSenderMock}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

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
    val friends: Seq[Friend] = Seq(Friend("John", EmailAddress("john.doe@foobar.com")))
    val emailSender = new EmailSenderMock()
    val birthdayGreetingsSender = new BirthdayGreetingsSender(friends, emailSender)

    birthdayGreetingsSender.sendGreetings()

    emailSender.emails.size shouldBe 1
    emailSender.emails.head.address shouldBe EmailAddress("john.doe@foobar.com")
  }

  "The greeting sender " should "send one email to the specified friend" in {
    val friends: Seq[Friend] = Seq(Friend("John", EmailAddress("john.doe@foobar.com")))
    val emailSender = new EmailSenderMock()
    val birthdayGreetingsSender = new BirthdayGreetingsSender(friends, emailSender)

    birthdayGreetingsSender.sendGreetings()

    emailSender.emails.size shouldBe 1
    emailSender.emails.head.text shouldBe
      """
        |Subject: Happy birthday!
        |
        | Happy birthday, dear John!
        |""".stripMargin
  }


}
