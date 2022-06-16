package walt.kata
package features.greeting

trait FriendRepository {
  def allFriends: Seq[Friend]
}
