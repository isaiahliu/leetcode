package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numRabbits(answers: IntArray): Int {
            val map = hashMapOf<Int, Int>()

            return answers.fold(0) { a, b ->
                val count = (map[b] ?: 0) + 1

                var result = a
                if (count == 1) {
                    result += b + 1
                }

                if (count == b + 1) {
                    map.remove(b)
                } else {
                    map[b] = count
                }

                result
            }
        }
    }

    measureTimeMillis {
        Solution().numRabbits(
            intArrayOf(1, 1, 2)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}