package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun networkBecomesIdle(edges: Array<IntArray>, patience: IntArray): Int {
            val adjacent = Array(patience.size) { hashSetOf<Int>() }

            edges.forEach { (from, to) ->
                adjacent[from].add(to)
                adjacent[to].add(from)
            }

            val distance = IntArray(patience.size) { Int.MAX_VALUE }

            val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
            queue.add(0 to 0)

            while (queue.isNotEmpty()) {
                val (index, time) = queue.poll()
                if (distance[index] > time) {
                    distance[index] = time

                    adjacent[index].forEach {
                        queue.add(it to time + 1)
                    }
                }
            }

            var result = 0

            patience.forEachIndexed { index, p ->
                var time = distance[index] * 2

                if (p > 0) {
                    //time = 4 p = 1
                    var newStart = time / p * p
                    if (newStart == time) {
                        newStart -= p
                    }

                    time += newStart
                }

                result = result.coerceAtLeast(time)
            }

            return result + 1
        }
    }

    expect {
        Solution().networkBecomesIdle(
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
            ), intArrayOf(0, 2, 1)
        )
    }
}