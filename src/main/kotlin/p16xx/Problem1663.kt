package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getSmallestString(n: Int, k: Int): String {
            val result = StringBuilder()

            var remainN = n
            var remainK = k

            val zCount = (remainK - remainN) / 25

            result.append("z".repeat(zCount))
            remainN -= zCount
            remainK -= zCount * 26

            if (remainN > 0) {
                val mid = remainK - remainN

                result.insert(0, 'a' + mid)

                remainN--
            }

            result.insert(0, "a".repeat(remainN))

            return result.toString()
        }
    }

    measureTimeMillis {
        Solution().getSmallestString(
            5, 73
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

