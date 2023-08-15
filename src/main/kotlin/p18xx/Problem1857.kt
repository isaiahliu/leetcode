package p18xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun largestPathValue(colors: String, edges: Array<IntArray>): Int {
            fun processColor(targetColor: Char): Int? {
                var result = 1
                val degrees = IntArray(colors.length)
                val route = Array(colors.length) { hashSetOf<Int>() }
                edges.forEach { (from, to) ->
                    degrees[to]++
                    route[from].add(to)
                }
                val routeColors = IntArray(colors.length)
                val queue = LinkedList<Int>()

                route.forEachIndexed { index, tos ->
                    if (tos.isNotEmpty() && degrees[index] == 0) {
                        queue.add(index)
                        if (colors[index] == targetColor) {
                            routeColors[index]++
                        }
                    }
                }

                while (queue.isNotEmpty()) {
                    val index = queue.poll()

                    route[index].forEach {
                        var colorCount = 0
                        if (colors[it] == targetColor) {
                            colorCount++
                        }

                        routeColors[it] = routeColors[it].coerceAtLeast(routeColors[index] + colorCount)
                        result = result.coerceAtLeast(routeColors[it])

                        degrees[it]--
                        if (degrees[it] == 0) {
                            queue.add(it)
                        }
                    }
                }

                return result.takeIf { degrees.none { it > 0 } }
            }

            var result = 1
            colors.toSet().forEach {
                processColor(it)?.also {
                    result = result.coerceAtLeast(it)
                } ?: return -1
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().largestPathValue(
            "keitgkggegyktyeytgyigkggktiigigkeyygtgytiygtkg", arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(1, 3),
                intArrayOf(3, 4),
                intArrayOf(4, 5),
                intArrayOf(5, 6),
                intArrayOf(3, 6),
                intArrayOf(5, 7),
                intArrayOf(6, 8),
                intArrayOf(5, 8),
                intArrayOf(7, 8),
                intArrayOf(8, 9),
                intArrayOf(7, 10),
                intArrayOf(8, 10),
                intArrayOf(9, 10),
                intArrayOf(10, 11),
                intArrayOf(9, 11),
                intArrayOf(7, 11),
                intArrayOf(5, 12),
                intArrayOf(11, 12),
                intArrayOf(11, 13),
                intArrayOf(13, 14),
                intArrayOf(12, 14),
                intArrayOf(12, 15),
                intArrayOf(10, 15),
                intArrayOf(14, 15),
                intArrayOf(7, 15),
                intArrayOf(9, 16),
                intArrayOf(13, 16),
                intArrayOf(12, 16),
                intArrayOf(15, 16),
                intArrayOf(11, 17),
                intArrayOf(14, 17),
                intArrayOf(16, 17),
                intArrayOf(15, 18),
                intArrayOf(14, 18),
                intArrayOf(17, 18),
                intArrayOf(18, 19),
                intArrayOf(14, 19),
                intArrayOf(13, 19),
                intArrayOf(14, 20),
                intArrayOf(15, 21),
                intArrayOf(12, 21),
                intArrayOf(20, 21),
                intArrayOf(19, 22),
                intArrayOf(20, 22),
                intArrayOf(21, 22),
                intArrayOf(22, 23),
                intArrayOf(19, 23),
                intArrayOf(11, 23),
                intArrayOf(18, 23),
                intArrayOf(13, 24),
                intArrayOf(23, 24),
                intArrayOf(21, 24),
                intArrayOf(24, 25),
                intArrayOf(13, 25),
                intArrayOf(23, 25),
                intArrayOf(15, 26),
                intArrayOf(23, 26),
                intArrayOf(25, 26),
                intArrayOf(24, 26),
                intArrayOf(26, 27),
                intArrayOf(25, 27),
                intArrayOf(26, 28),
                intArrayOf(27, 28),
                intArrayOf(20, 28),
                intArrayOf(23, 28),
                intArrayOf(11, 28),
                intArrayOf(23, 29),
                intArrayOf(29, 30),
                intArrayOf(25, 31),
                intArrayOf(26, 31),
                intArrayOf(15, 32),
                intArrayOf(30, 32),
                intArrayOf(31, 33),
                intArrayOf(27, 33),
                intArrayOf(30, 33),
                intArrayOf(28, 33),
                intArrayOf(29, 34),
                intArrayOf(32, 35),
                intArrayOf(33, 35),
                intArrayOf(34, 35),
                intArrayOf(35, 36),
                intArrayOf(13, 36),
                intArrayOf(34, 36),
                intArrayOf(30, 37),
                intArrayOf(36, 37),
                intArrayOf(35, 37),
                intArrayOf(24, 37),
                intArrayOf(35, 38),
                intArrayOf(34, 39),
                intArrayOf(37, 39),
                intArrayOf(37, 40),
                intArrayOf(39, 41),
                intArrayOf(37, 41),
                intArrayOf(41, 42),
                intArrayOf(38, 42),
                intArrayOf(40, 43),
                intArrayOf(43, 44),
                intArrayOf(39, 44),
                intArrayOf(35, 44),
                intArrayOf(38, 45),
                intArrayOf(44, 45),
                intArrayOf(26, 45)
            )
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
