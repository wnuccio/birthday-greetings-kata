package walt.kata

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters._

class BirthdayGreetingsAcceptanceTest extends AnyFlatSpec with should.Matchers {

  "The app" should "read a file of friends and send an email for each birthday" in {
    val output = Paths.get("src/main/resources/test/emails.txt")
    Files.deleteIfExists(output)
    Files.createFile(output)

    BirthdayGreetings.main(Array[String]("test/friends.txt", "test/clock.txt", "test/emails.txt"))

    Files.exists(output) shouldBe true
    val lines = Files.readAllLines(output).asScala
    lines.size shouldBe 2
    lines(0) shouldBe "email to: Mary, mary.ann@foobar.com"
    lines(1) shouldBe "email to: Walt, walt.nuc@foobar.com"
  }
}
