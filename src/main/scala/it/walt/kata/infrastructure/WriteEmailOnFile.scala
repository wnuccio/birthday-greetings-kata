package it.walt.kata.infrastructure

import it.walt.kata.features.email._

import java.io.{ File, FileWriter }
import java.nio.file.Files

class WriteEmailOnFile(outputFile: String) extends EmailGateway {
  lazy val writer: FileWriter = {
    val file   = new File(outputFile)
    Files.deleteIfExists(file.toPath)
    Files.createFile(file.toPath)
    val writer = new FileWriter(file)
    writer
  }

  override def sendEmail(email: Email): Unit = {
    writer.append(EmailFunctions.toString(email))
    writer.append("\n")
    writer.flush()
    super.sendEmail(email)
  }
}
