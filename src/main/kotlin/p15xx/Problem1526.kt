package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minNumberOperations(target: IntArray): Int {
            var result = 0

            var pre = 0

            target.forEach {
                result += (it - pre).coerceAtLeast(0)

                pre = it
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minNumberOperations(
            intArrayOf()
        ).also { println(it) }
    }
}

