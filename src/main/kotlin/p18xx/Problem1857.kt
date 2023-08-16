package p18xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun largestPathValue(colors: String, edges: Array<IntArray>): Int {
            var result = 1
            val degrees = IntArray(colors.length)
            val route = Array(colors.length) { hashSetOf<Int>() }
            edges.forEach { (from, to) ->
                degrees[to]++
                route[from].add(to)
            }
            val routeColors = Array(colors.length) { IntArray(26) }
            val queue = LinkedList<Int>()

            route.forEachIndexed { index, tos ->
                if (tos.isNotEmpty() && degrees[index] == 0) {
                    queue.add(index)
                    routeColors[index][colors[index] - 'a']++
                }
            }

            while (queue.isNotEmpty()) {
                val index = queue.poll()

                route[index].forEach {
                    routeColors[index].forEachIndexed { color, count ->
                        (count + if (color == colors[it] - 'a') {
                            1
                        } else {
                            0
                        }).also { max ->
                            routeColors[it][color] = routeColors[it][color].coerceAtLeast(max)
                            result = result.coerceAtLeast(max)
                        }
                    }

                    degrees[it]--
                    if (degrees[it] == 0) {
                        queue.add(it)
                    }
                }
            }

            return result.takeIf { degrees.none { it > 0 } } ?: -1
        }
    }

    measureTimeMillis {
        Solution().largestPathValue(
            "abaca", arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4),
            )
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
