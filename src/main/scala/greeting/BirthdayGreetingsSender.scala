package walt.kata
package greeting

class BirthdayGreetingsSender(friends: Seq[Friend], emailSender: EmailSender) {
  def sendGreetings(): Unit = {
    emailSender.sendGreetingsTo(friends)
  }

}
