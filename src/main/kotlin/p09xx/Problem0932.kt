package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        val cache = hashMapOf<Int, IntArray>()
        fun beautifulArray(n: Int): IntArray {
            return when (n) {
                1 -> intArrayOf(1)
                2 -> intArrayOf(1, 2)
                3 -> intArrayOf(1, 3, 2)
                in cache -> cache[n] ?: intArrayOf()
                else -> {
                    val left = n / 2 + n % 2
                    val right = n / 2

                    val result =
                        (beautifulArray(left).map { it * 2 - 1 } + beautifulArray(right).map { it * 2 }).toIntArray()

                    cache[n] = result

                    result
                }
            }
        }
    }

    measureTimeMillis {
        Solution().beautifulArray(
            5
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}