package walt.kata
package email

class EmailSenderMock() extends EmailSender {
  var emails: Seq[Email] = Seq.empty

  override protected def sendEmail(email: Email): Unit = emails = emails :+ email
}
