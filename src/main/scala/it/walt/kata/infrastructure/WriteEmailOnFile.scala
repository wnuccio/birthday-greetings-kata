package it.walt.kata.infrastructure

import it.walt.kata.features.email._

import java.io.{ FileWriter, PrintWriter }

class WriteEmailOnFile(outputFile: String) extends EmailGateway {
  private lazy val writer = new PrintWriter(new FileWriter(s"src/main/resources/$outputFile"))

  override def sendEmail(email: Email): Unit = {
    email match {
      case HappyBirthdayEmail(_)                            =>
        writer.append(s"happy birthday to: ${email.sentTo}, ${email.address.value}\n")
      case BirthdayRemainderEmail(_, birthdayFriend)        =>
        writer.append(s"remainder to: ${email.sentTo} for birthday of: ${birthdayFriend.firstName} \n")
      case BirthdaySingleRemainderEmail(_, birthdayFriends) =>
        writer.append(s"single remainder to: ${email.sentTo} for birthdays of: ${birthdayFriends.map(_.firstName)} \n")
    }

    writer.flush()
    super.sendEmail(email)
  }
}
