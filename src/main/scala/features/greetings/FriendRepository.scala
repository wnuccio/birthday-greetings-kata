package walt.kata
package features.greetings

trait FriendRepository {
  def allFriends: Seq[Friend]
}
