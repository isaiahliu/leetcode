package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun secondMinimum(n: Int, edges: Array<IntArray>, time: Int, change: Int): Int {
            val adjacent = Array(n + 1) { hashSetOf<Int>() }

            edges.forEach { (from, to) ->
                adjacent[from].add(to)
                adjacent[to].add(from)
            }

            val route = Array(n + 1) {
                intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE)
            }

            val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

            queue.add(1 to 0)

            val result = TreeSet<Int>()
            loop@ while (queue.isNotEmpty()) {
                val (index, arrive) = queue.poll()

                if (index == n) {
                    result.add(arrive)
                }

                var leave = arrive

                if ((leave / change) % 2 == 1) {
                    leave = (leave / change + 1) * change
                }

                val r = route[index]
                when {
                    leave < r[0] -> r[0] = leave
                    leave == r[0] -> continue@loop
                    leave < r[1] -> r[1] = leave
                    else -> continue@loop
                }

                adjacent[index].forEach {
                    queue.add(it to (leave + time))
                }
            }

            result.pollFirst()

            return result.pollFirst() ?: 0
        }
    }

    expect {
        Solution().secondMinimum(
            5, arrayOf(), 1, 2
        )
    }
}