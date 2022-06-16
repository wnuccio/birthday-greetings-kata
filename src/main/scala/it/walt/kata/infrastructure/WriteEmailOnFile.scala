package it.walt.kata.infrastructure

import it.walt.kata.features.email.{Email, EmailGateway}

import java.io.{FileWriter, PrintWriter}

class WriteEmailOnFile(outputFile: String) extends EmailGateway {
  private lazy val writer = new PrintWriter(new FileWriter(s"src/main/resources/$outputFile"))

  override def sendEmail(email: Email): Unit = {
    writer.append(s"email to: ${email.personName}, ${email.address.value}\n")
    writer.flush()
    super.sendEmail(email)
  }
}