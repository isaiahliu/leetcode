package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun thousandSeparator(n: Int): String {
            val str = n.toString()

            val result = StringBuilder()
            var remain = (str.length + 2) % 3

            str.forEach {
                result.append(it)
                if (remain == 0) {
                    result.append('.')
                    remain = 3
                }
                remain--
            }

            return result.dropLast(1).toString()
        }
    }

    measureTimeMillis {
        Solution().thousandSeparator(
            9466441
        ).also { println(it) }
    }
}

