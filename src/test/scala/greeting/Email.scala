package walt.kata
package greeting

case class Email(personName: String, address: EmailAddress) {
  private val textTemplate: String =
    """
      |Subject: Happy birthday!
      |
      | Happy birthday, dear <first_name>!
      |""".stripMargin

  val text: String = textTemplate.replace("<first_name>", personName)
}
