package it.walt.kata.infrastructure

import it.walt.kata.features.date.Clock

import java.time.LocalDate

class RealClock() extends Clock {
  override def today: LocalDate = LocalDate.now()
}

