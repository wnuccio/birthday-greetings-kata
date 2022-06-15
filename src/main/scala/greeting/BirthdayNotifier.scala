package walt.kata
package greeting

class BirthdayNotifier(friends: Seq[Friend], emailSender: EmailSender) {
  def sendGreetings(): Unit = {
    emailSender.sendGreetingsTo(friends)
  }

}
