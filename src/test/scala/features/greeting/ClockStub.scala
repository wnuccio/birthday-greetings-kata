package walt.kata.features.greeting

import walt.kata.features.date.Clock

import java.time.LocalDate

object ClockStub {
  def today(today: LocalDate) = new ClockStub(today)
}

class ClockStub(override val today: LocalDate) extends Clock {

}
