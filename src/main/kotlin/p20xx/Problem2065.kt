package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximalPathQuality(values: IntArray, edges: Array<IntArray>, maxTime: Int): Int {
            val adjacent = Array(values.size) { hashMapOf<Int, Int>() }

            edges.forEach { (from, to, time) ->
                adjacent[from][to] = time
                adjacent[to][from] = time
            }

            //index, time, route
            val queue = PriorityQueue<Pair<Pair<Int, Int>, Pair<Set<Int>, Int>>>(compareBy { it.first.second })
            queue.add((0 to 0) to (setOf(0) to values[0]))

            val bestRoutes = IntArray(values.size) { -1 }

            while (queue.isNotEmpty()) {
                val (p1, p2) = queue.poll()
                val (node, time) = p1
                val (route, value) = p2

                if (bestRoutes[node] >= value) {
                    continue
                }

                bestRoutes[node] = value

                adjacent[node].forEach { (to, cost) ->
                    (time + cost).takeIf { it <= maxTime }?.also {
                        var newValue = value
                        if (to !in route) {
                            newValue += values[to]
                        }
                        queue.add((to to it) to (route + to to newValue))
                    }
                }
            }

            return bestRoutes[0]
        }
    }

    expect {
        Solution().maximalPathQuality(
            intArrayOf(), arrayOf(), 100
        )
    }
}