package walt.kata
package greeting

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class BirthdayNotifierTest extends AnyFlatSpec with should.Matchers {

  "The BirthdayNotifier " should "send one greeting" in {
    val friends: Seq[Friend] = Seq(Friend("John"))
    val emailSender = new EmailSenderMock()
    val birthdayNotifier = new BirthdayNotifier(friends, emailSender)

    birthdayNotifier.sendGreetings()

    emailSender.greetings.size shouldBe 1
    emailSender.greetings.head shouldBe Greeting("John")
  }


}
