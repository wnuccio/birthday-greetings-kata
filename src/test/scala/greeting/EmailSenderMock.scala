package walt.kata
package greeting

class EmailSenderMock() extends EmailSender{
  def greetings: Seq[Greeting] = Seq.empty
}
