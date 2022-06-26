package it.walt.kata.features.greetings

import it.walt.kata.features.email.EmailAddress

import java.time.LocalDate
import java.time.Month.FEBRUARY

case class Friend(firstName: String, lastName: String, birthdate: LocalDate, emailAddress: EmailAddress) {
  val fullName: String = s"$firstName $lastName"

  def isBirthdate(today: LocalDate): Boolean = {
    isRegularBirthdate(today) || isBirthdateForLeapYears(today)
  }

  // A year is leap if divisible for 400 or it is divisible for 4 but not for 100.
  // Some samples of leap years: 1600, 1604, 1608, 1696.
  // Some sample of non leap year: 1700 (divisible both for 4 and for 100)
  private def isBirthdateForLeapYears(today: LocalDate): Boolean = {
    val isBornOn29Feb = birthdate.getMonth.equals(FEBRUARY) && birthdate.getDayOfMonth == 29
    val currentIsLeapYear = today.getYear % 400 == 0 || (today.getYear % 4 == 0 && today.getYear % 100 != 0)
    isBornOn29Feb && ! currentIsLeapYear
  }

  private def isRegularBirthdate(today: LocalDate) = {
    val sameMonth = today.getMonth.equals(birthdate.getMonth)
    val sameDay = today.getDayOfMonth.equals(birthdate.getDayOfMonth)
    sameMonth && sameDay
  }
}
