package p02xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isUgly(n: Int): Boolean {
            if (n < 1) {
                return false
            }
            var t = n
            while (t % 2 == 0) {
                t /= 2
            }

            while (t % 3 == 0) {
                t /= 3
            }

            while (t % 5 == 0) {
                t /= 5
            }

            return t == 1
        }
    }

    measureTimeMillis {
        Solution().isUgly(
            1
        ).also { println(it) }
    }
}

