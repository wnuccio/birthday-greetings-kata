package walt.kata
package greeting

import java.time.LocalDate

object ClockStub {
  def today(today: LocalDate) = new ClockStub(today)
}

class ClockStub(override val today: LocalDate) extends Clock {

}
