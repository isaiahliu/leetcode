package p07xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maxChunksToSorted(arr: IntArray): Int {
            val queue = LinkedList<Pair<Int, Int>>()

            arr.forEach { num ->
                val last = queue.peekLast()?.second?.coerceAtLeast(num) ?: num
                while (queue.isNotEmpty() && queue.peekLast().second > num) {
                    queue.pollLast()
                }

                queue.add(num to last)
            }

            return queue.size
        }
    }

    expect {
        Solution().maxChunksToSorted(
            intArrayOf(1, 0, 1, 3, 2)
        )
    }
}