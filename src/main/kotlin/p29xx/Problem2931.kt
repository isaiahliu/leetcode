package p29xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maxSpending(values: Array<IntArray>): Long {
            val queue = PriorityQueue<Pair<Int, Int>>(compareBy { (r, c) -> values[r][c] })

            values.indices.forEach {
                queue.add(it to values[it].lastIndex)
            }

            var day = 1L
            var result = 0L

            while (queue.isNotEmpty()) {
                val (r, c) = queue.poll()

                result += values[r][c] * day

                if (c > 0) {
                    queue.add(r to c - 1)
                }

                day++
            }

            return result
        }
    }

    expect {
        Solution().maxSpending(
            arrayOf()
        )
    }
}
