package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isPowerOfThree(n: Int): Boolean {
            return n > 0 && 1162261467 % n == 0
        }
    }

    measureTimeMillis {
        Solution().isPowerOfThree(3).also { println(it) }
    }
}

