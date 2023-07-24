package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numOfMinutes(n: Int, headID: Int, manager: IntArray, informTime: IntArray): Int {
            val cache = IntArray(n) { -1 }

            cache[headID] = 0

            fun sendMessage(index: Int): Int {
                val m = manager[index]

                if (cache[index] >= 0) {
                    return cache[index]
                }

                val result = informTime[m] + sendMessage(m)

                cache[index] = result

                return result
            }

            manager.indices.forEach { sendMessage(it) }

            return cache.max()
        }
    }

    measureTimeMillis {
        Solution().numOfMinutes(
            5, 5, intArrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

