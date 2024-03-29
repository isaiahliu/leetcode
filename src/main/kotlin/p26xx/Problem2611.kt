package p26xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun miceAndCheese(reward1: IntArray, reward2: IntArray, k: Int): Int {
            var result = 0

            val queue = PriorityQueue<Int>(compareBy { reward1[it] - reward2[it] })

            reward1.indices.forEach {
                result += reward1[it]

                queue.add(it)

                if (queue.size > k) {
                    queue.poll().also {
                        result += reward2[it] - reward1[it]
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().miceAndCheese(
            intArrayOf(), intArrayOf(), 1
        )
    }
}
