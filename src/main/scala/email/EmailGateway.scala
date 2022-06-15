package walt.kata
package email

trait EmailGateway {
  def sendEmail(email: Email): Unit
}
