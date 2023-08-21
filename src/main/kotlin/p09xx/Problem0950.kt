package p09xx

import java.util.*
import util.expect

fun main() {
class Solution {
    fun deckRevealedIncreasing(deck: IntArray): IntArray {
        val queue = LinkedList<Int>()
        deck.sortedDescending().forEach {
            queue.pollLast()?.also {
                queue.push(it)
            }

            queue.push(it)
        }

        return queue.toIntArray()
    }
}

    expect {
        Solution().deckRevealedIncreasing(
            intArrayOf(2, 4, 6, 0)
        )
    }
}
