package walt.kata
package greeting

class EmailSenderMock() extends EmailSender {
  var emails: Seq[Email] = Seq.empty

  override protected def sendEmail(email: Email): Unit = emails = emails :+ email
}
