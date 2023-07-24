package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun frogPosition(n: Int, edges: Array<IntArray>, t: Int, target: Int): Double {
            val map = hashMapOf<Int, MutableSet<Int>>()

            edges.forEach { (from, to) ->
                map.computeIfAbsent(from) { hashSetOf() }.add(to)
                map.computeIfAbsent(to) { hashSetOf() }.add(from)
            }

            var result = 0.0
            fun walk(second: Int, pos: Int, possibility: Double, route: Set<Int>) {
                val tos = map[pos].orEmpty() - route

                if (second == t || tos.isEmpty()) {
                    if (pos == target) {
                        result += possibility
                    }
                } else {
                    val nextPossibility = possibility / tos.size
                    val nextRoute = route + pos

                    tos.forEach {
                        walk(second + 1, it, nextPossibility, nextRoute)
                    }
                }
            }

            walk(0, 1, 1.0, emptySet())
            return result
        }
    }

    measureTimeMillis {
        Solution().frogPosition(
            1, arrayOf(), 1, 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

