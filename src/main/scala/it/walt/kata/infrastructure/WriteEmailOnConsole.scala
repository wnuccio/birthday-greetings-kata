package it.walt.kata.infrastructure

import it.walt.kata.features.email.{Email, EmailGateway}

trait WriteEmailOnConsole extends EmailGateway {
  override def sendEmail(email: Email): Unit = {
    print(s"email to: ${email.personName}, ${email.address.value}\n")
    super.sendEmail(email)
  }
}
