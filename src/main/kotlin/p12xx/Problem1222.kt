package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun queensAttacktheKing(queens: Array<IntArray>, king: IntArray): List<List<Int>> {
            val q = queens.map { (r, c) -> r to c }.toSet()

            val (r, c) = king

            val result = arrayListOf<List<Int>>()
            arrayOf(
                -1 to -1,
                -1 to 0,
                -1 to 1,
                0 to -1,
                0 to 1,
                1 to -1,
                1 to 0,
                1 to 1,
            ).forEach { (deltaR, deltaC) ->
                var step = 1

                while (true) {
                    val newR = r + deltaR * step
                    val newC = c + deltaC * step

                    when {
                        newR !in (0..8) -> {
                            return@forEach
                        }

                        newC !in (0..8) -> {
                            return@forEach
                        }

                        newR to newC in q -> {
                            result.add(listOf(newR, newC))

                            return@forEach
                        }
                    }

                    step++
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().queensAttacktheKing(
            arrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
