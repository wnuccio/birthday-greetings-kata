package it.walt.kata.features.email

class EmailGatewayMock() extends EmailGateway {

  private var emails: Seq[Email] = Seq.empty

  override def sendEmail(email: Email): Unit = emails = emails :+ email

  def emailSent(): Int = emails.size

  def emailSentToAddress(emailAddress: String): Boolean = {
    require(emails.size == 1)

    emails.head.address.value == emailAddress
  }

  def emailSentWithText(emailText: String): Boolean = {
    require(emails.size == 1)

    emails.head.text == emailText
  }

  def emailSentTo(names: String*): Boolean = {
    require(emails.size == names.size)

    names.sorted == emails.map(_.sentTo).sorted
  }

  def remainderSentTo(friendName: String, birthdayFriendName: String): Boolean = {
    val namesInEmails: Seq[(String, String)] = emails.map {
      case BirthdayRemainderEmail(friend, birthdayFriend) => (friend.firstName, birthdayFriend.firstName)
      case _                                              => throw new IllegalStateException("unexptected email type")
    }

    namesInEmails.contains((friendName, birthdayFriendName))
  }

  def singleRemainderSentTo(
    friendName: String,
    birthdayFriendFullName1: String,
    birthdayFriendFullName2: String = "",
    birthdayFriendFullName3: String = ""
  ): Boolean = {

    val namesInEmails: Seq[(String, String, String, String)] = emails.map {
      case BirthdaySingleRemainderEmail(friend, birthdayFriends) =>
        require(birthdayFriends.size <= 3)
        val name1 = if (birthdayFriends.size >= 1) birthdayFriends(0).fullName else ""
        val name2 = if (birthdayFriends.size >= 2) birthdayFriends(1).fullName else ""
        val name3 = if (birthdayFriends.size == 3) birthdayFriends(2).fullName else ""
        (friend.firstName, name1, name2, name3)
      case _                                                     => throw new IllegalStateException("unexptected email type")
    }

    namesInEmails.contains((friendName, birthdayFriendFullName1, birthdayFriendFullName2, birthdayFriendFullName3))
  }
}
