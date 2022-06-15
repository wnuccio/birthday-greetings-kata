package walt.kata
package email

class EmailGatewayMock() extends EmailGateway {
  var emails: Seq[Email] = Seq.empty

  override def sendEmail(email: Email): Unit = emails = emails :+ email
}
