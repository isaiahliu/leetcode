package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestDiverseString(a: Int, b: Int, c: Int): String {
            val array = arrayOf(intArrayOf(a, 0), intArrayOf(b, 1), intArrayOf(c, 2))

            array.sortWith(compareByDescending { it[0] })

            var last = -1

            val result = StringBuilder()

            while (array[0][0] > 0) {
                if (array[0][1] != last) {
                    repeat(array[0][0].coerceAtMost(2)) {
                        result.append('a' + array[0][1])
                        array[0][0]--
                    }

                    last = array[0][1]
                } else if (array[1][0] > 0) {
                    result.append('a' + array[1][1])
                    array[1][0]--
                    last = array[1][1]
                } else {
                    break
                }
                array.sortWith(compareByDescending { it[0] })
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

