package p07xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun dailyTemperatures(temperatures: IntArray): IntArray {
            val queue = LinkedList<Pair<Int, Int>>()

            for (i in temperatures.lastIndex downTo 0) {
                val temp = temperatures[i]

                while (queue.peek()?.first?.takeIf { it <= temp } != null) {
                    queue.pop()
                }

                temperatures[i] = queue.peek()?.second?.let { it - i } ?: 0

                queue.push(temp to i)
            }

            return temperatures
        }
    }

    expect {
        Solution().dailyTemperatures(
            intArrayOf(73, 74, 75, 71, 69, 72, 76, 73)
        ).toList()
    }
}