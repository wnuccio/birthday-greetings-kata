package it.walt.kata

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

import java.io.File
import java.nio.file.Files
import scala.jdk.CollectionConverters._

class BirthdayGreetingsAcceptanceTest extends AnyFlatSpec with should.Matchers {

  "The app" should "read a file of friends and send an email for each birthday" in {
    val outputFile = new File("src/test/resources/emails.txt")
    Files.deleteIfExists(outputFile.toPath)

    BirthdayGreetings.main(Array[String]("friends.txt", "clock.txt", outputFile.toPath.toString))

    Files.exists(outputFile.toPath) shouldBe true
    val lines = Files.readAllLines(outputFile.toPath).asScala
    lines.size shouldBe 2
    lines(0) shouldBe "happy birthday to: Mary, mary.ann@foobar.com"
    lines(1) shouldBe "happy birthday to: Walt, walt.nuc@foobar.com"
  }
}
