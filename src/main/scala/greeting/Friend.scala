package walt.kata
package greeting

import email.EmailAddress

import java.time.LocalDate

case class Friend(firstName: String, emailAddress: EmailAddress, birthdate: LocalDate) {
  def isBirthdate(today: LocalDate): Boolean = {
    val sameMonth = today.getMonth.equals(birthdate.getMonth)
    val sameDay = today.getDayOfMonth.equals(birthdate.getDayOfMonth)
    sameMonth && sameDay
  }

}
