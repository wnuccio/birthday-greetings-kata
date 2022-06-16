package it.walt.kata.features.email

trait EmailGateway {
  def sendEmail(email: Email): Unit = {}
}
