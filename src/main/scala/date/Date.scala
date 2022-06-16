package walt.kata
package date

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Date {
  def apply(dateString: String): LocalDate =
      LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
}
