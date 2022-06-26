package it.walt.kata.infrastructure

import it.walt.kata.features.email.{ Email, EmailFunctions, EmailGateway }

trait WriteEmailOnConsole extends EmailGateway {
  override def sendEmail(email: Email): Unit = {
    println(EmailFunctions.toString(email))
    super.sendEmail(email)
  }
}
