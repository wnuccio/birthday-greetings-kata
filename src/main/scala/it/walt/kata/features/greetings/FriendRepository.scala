package it.walt.kata.features.greetings

trait FriendRepository {
  def allFriends: Seq[Friend]
}
