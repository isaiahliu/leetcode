package p25xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun putMarbles(weights: IntArray, k: Int): Long {
            val queue1 = PriorityQueue<Int>()
            val queue2 = PriorityQueue<Int>(compareByDescending { it })

            for (i in 0 until weights.lastIndex) {
                queue1.add(weights[i] + weights[i + 1])
                queue2.add(weights[i] + weights[i + 1])
            }

            var result = 0L

            repeat(k - 1) {
                result += queue2.poll()
                result -= queue1.poll()
            }

            return result
        }
    }

    expect {
        Solution().putMarbles(
            intArrayOf(), 1
        )
    }
}