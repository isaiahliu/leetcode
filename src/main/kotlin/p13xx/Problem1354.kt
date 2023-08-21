package p13xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun isPossible(target: IntArray): Boolean {
            if (target.size == 1) {
                return target[0] == 1
            }

            val queue = PriorityQueue<Long>(compareByDescending { it })

            var sum = 0L
            target.forEach {
                sum += it
                queue.add(it.toLong())
            }

            while (queue.peek() > 1) {
                val max = queue.poll()
                sum -= max

                val secondMax = queue.peek()

                (max - ((max - secondMax) / sum).coerceAtLeast(1) * sum).takeIf { it > 0 }?.also {
                    sum += it
                    queue.add(it)
                } ?: return false
            }

            return true
        }
    }

    expect {
        Solution().isPossible(
            intArrayOf(1, 1000000000)
        )
    }
}

