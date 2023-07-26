package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestDiverseString(a: Int, b: Int, c: Int): String {
            val array = arrayOf(intArrayOf(0, a), intArrayOf(1, b), intArrayOf(2, c))

            array.sortWith(compareByDescending { it[1] })

            var last = -1

            val result = StringBuilder()

            while (array[0][1] > 0) {
                var idx = 0
                var count = 2

                when {
                    array[0][0] != last -> {
                    }

                    array[1][1] > 0 -> {
                        idx++
                        count--
                    }

                    else -> {
                        break
                    }
                }

                repeat(array[idx][1].coerceAtMost(count)) {
                    result.append('a' + array[idx][0])
                    array[idx][1]--
                }

                last = array[idx][0]

                array.sortWith(compareByDescending { it[1] })
            }

            return result.toString()
        }
    }

    measureTimeMillis {
        Solution().longestDiverseString(
            1, 2, 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

