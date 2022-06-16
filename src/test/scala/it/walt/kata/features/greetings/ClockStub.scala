package it.walt.kata.features.greetings

import it.walt.kata.features.date.Clock

import java.time.LocalDate

object ClockStub {
  def today(today: LocalDate) = new ClockStub(today)
}

class ClockStub(override val today: LocalDate) extends Clock {

}
