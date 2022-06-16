package walt.kata
package features.email

trait EmailGateway {
  def sendEmail(email: Email): Unit
}
