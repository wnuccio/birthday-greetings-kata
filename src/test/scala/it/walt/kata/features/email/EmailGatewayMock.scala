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

  def emailSent(): Int = emails.size

  def sentRemaindersTo(friendName: String, birthdayFriendName: String): Boolean = {
    val namesInEmails: Seq[(String, String)] = emails.map {
      case BirthdayRemainderEmail(friend, birthdayFriend) => (friend.firstName, birthdayFriend.firstName)
    }

    namesInEmails.contains((friendName, birthdayFriendName))
  }
}
