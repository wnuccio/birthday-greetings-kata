package it.walt.kata.features.email

class EmailGatewayMock() extends EmailGateway {
  private var emails: Seq[Email] = Seq.empty

  override def sendEmail(email: Email): Unit = emails = emails :+ email

  def sentEmailToAddress(emailAddress: String): Boolean = {
    require(emails.size == 1)

    emails.head.address.value == emailAddress
  }

  def sentEmailWithText(emailText: String): Boolean = {
    require(emails.size == 1)

    emails.head.text == emailText
  }

  def sentEmailTo(names: String*): Boolean = {
    require(emails.size == names.size)

    names.sorted == emails.map(_.sentTo).sorted
  }

  def sentRemaindersTo(sentTo: String, birthdays: Seq[String]): Boolean = {
    require(emails.size == birthdays.size)
    require(emails.head.sentTo == sentTo)

    val namesInEmails: Seq[String] = emails.map {
      case BirthdayRemainderEmail(_, birthdayFriend) => birthdayFriend.firstName
      case _ => ""
    }

    birthdays.sorted == namesInEmails.sorted
  }

  def sentRemaindersTo(sentTo: Seq[String], birthdays: Seq[String]): Boolean = {
    require(emails.size == sentTo.size * birthdays.size)

    val namesInEmails: Seq[(String, String)] = emails.map {
      case BirthdayRemainderEmail(friend, birthdayFriend) => (friend.firstName, birthdayFriend.firstName)
    }

    val expectedNames = for (
      friendName <- sentTo;
      birthdayName <- birthdays
    ) yield {
      (friendName, birthdayName)
    }

    namesInEmails.sorted == expectedNames.sorted
  }

}
