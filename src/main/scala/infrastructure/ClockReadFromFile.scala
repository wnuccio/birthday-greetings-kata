package walt.kata.infrastructure

import walt.kata.features.date.{Clock, Date}

import java.time.LocalDate
import scala.io.Source

class ClockReadFromFile(clockFile: String) extends Clock {
  override def today: LocalDate = Date(Source.fromResource(clockFile).getLines().next())
}

