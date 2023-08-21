package p19xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun minStoneSum(piles: IntArray, k: Int): Int {
            val queue = PriorityQueue<Int>(compareByDescending { it })

            var result = 0
            piles.forEach {
                result += it
                if (it > 1) {
                    queue.add(it)
                }
            }

            repeat(k) {
                val stones = queue.poll() ?: return result

                (stones / 2).also {
                    result -= it

                    (stones - it).takeIf { it > 1 }?.also { queue.add(it) }
                }
            }
            return result
        }
    }

    expect {
        Solution().minStoneSum(
            intArrayOf(), 1
        )
    }
}