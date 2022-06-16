package walt.kata
package features.greeting

import java.time.LocalDate

object ClockStub {
  def today(today: LocalDate) = new ClockStub(today)
}

class ClockStub(override val today: LocalDate) extends Clock {

}
