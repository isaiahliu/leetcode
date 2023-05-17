package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun prisonAfterNDays(cells: IntArray, n: Int): IntArray {
            var t = cells

            val visited = hashMapOf<String, Int>()
            val history = arrayListOf<IntArray>()

            var day = 0

            while (day < n) {
                t = IntArray(t.size) {
                    when (it) {
                        0, t.lastIndex -> 0
                        else -> 1 - (t[it - 1] xor t[it + 1])
                    }
                }

                val str = t.joinToString("")

                if (str in visited) {
                    val happend = visited[str] ?: 0

                    return history[happend + (n - day - 1) % (history.size - happend)]
                } else {
                    visited[str] = day
                    history.add(t)
                }

                day++
            }

            return t
        }
    }

    measureTimeMillis {
        Solution().prisonAfterNDays(
            intArrayOf(1, 0, 0, 1, 0, 0, 1, 0),
            1000000000
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
