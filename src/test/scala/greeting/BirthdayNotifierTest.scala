package walt.kata
package greeting

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

/***
 * Test case list:
 * - one friend -> one greeting with verification of the email address
 * - one friend -> one greeting with verification of the message
 * - one friend -> no greeting if today is not her birthday
 * - two friend -> one greeting since it is the birthday of the first one
 *
 * Done
 * - one friend -> one greeeting
 */

class BirthdayNotifierTest extends AnyFlatSpec with should.Matchers {

  "The BirthdayNotifier " should "send one greeting" in {
    val friends: Seq[Friend] = Seq(Friend("John"))
    val emailSender = new EmailSenderMock()
    val birthdayNotifier = new BirthdayNotifier(friends, emailSender)

    birthdayNotifier.sendGreetings()

    emailSender.greetings.size shouldBe 1
    emailSender.greetings.head shouldBe Greeting("John")
  }

  "The BirthdayNotifier " should "send one greeting to the specified address" in {
    val friends: Seq[Friend] = Seq(Friend("John", EmailAddress("john.doe@foobar.com")))
    val emailSender = new EmailSenderMock()
    val birthdayNotifier = new BirthdayNotifier(friends, emailSender)

    birthdayNotifier.sendGreetings()

    emailSender.emails.size shouldBe 1
    val email: Email = emailSender.emails.head
    email.address shouldBe EmailAddress("john.doe@foobar.com")
  }

  "The BirthdayNotifier " should "send one greeting to the specified friend" in {
    val friends: Seq[Friend] = Seq(Friend("John", EmailAddress("john.doe@foobar.com")))
    val emailSender = new EmailSenderMock()
    val birthdayNotifier = new BirthdayNotifier(friends, emailSender)

    birthdayNotifier.sendGreetings()

    emailSender.emails.size shouldBe 1
    val email: Email = emailSender.emails.head
    email.text shouldBe
      """
        |Subject: Happy birthday!
        |
        | Happy birthday, dear John!
        |""".stripMargin
  }


}
