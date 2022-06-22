package it.walt.kata.features.greetings

import it.walt.kata.features.date.Date
import it.walt.kata.features.email.{EmailGateway, EmailSender}
import it.walt.kata.features.greetings.FriendForTest.friendRepository

object BirthdayGreetingsFacadeFactory {
  def createGreetingsFacade(today: String, friends: Seq[Friend], emailGateway: EmailGateway): BirthdayGreetingsFacade = {
    val clock = ClockStub.today(Date(today))
    val emailSender = new EmailSender(emailGateway)
    new BirthdayGreetingsFacade(friendRepository(friends), clock, emailSender)
  }

}