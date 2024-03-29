package p17xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maxAverageRatio(classes: Array<IntArray>, extraStudents: Int): Double {
            val queue = PriorityQueue<Pair<Double, Double>>(compareByDescending { (p, t) ->
                (p + 1) / (t + 1) - p / t
            })

            classes.forEach { (p, t) ->
                queue.offer(p.toDouble() to t.toDouble())
            }

            repeat(extraStudents) {
                queue.poll().also { (p, t) ->
                    queue.offer(p + 1 to t + 1)
                }
            }

            return queue.map { it.first / it.second }.average()
        }
    }

    expect {
        Solution().maxAverageRatio(
            arrayOf(), 1
        )
    }
}
