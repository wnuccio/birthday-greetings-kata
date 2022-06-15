package walt.kata

import greeting.{BirthdayNotifier, EmailSenderMock, Greeting}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import scala.io.{BufferedSource, Source}

class BirthdayGreetingsAcceptanceTest extends AnyFlatSpec with should.Matchers {

  "The BirthdayNotifier " should "send one greeting" in {
    val friendsFile: BufferedSource = Source.fromResource("friends.txt")
    val emailSender = new EmailSenderMock()
    val birthdayNotifier = new BirthdayNotifier(friendsFile, emailSender)

    birthdayNotifier.sendGreetings()

    emailSender.greetings.size shouldBe 1
    emailSender.greetings.head shouldBe new Greeting("John")
  }
}
