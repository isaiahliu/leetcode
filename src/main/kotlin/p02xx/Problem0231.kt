package p02xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isPowerOfTwo(n: Int): Boolean {
            if (n < 0) {
                return false
            }
            return Integer.bitCount(n) == 1
        }
    }

    measureTimeMillis {
        Solution().isPowerOfTwo(1).also { println(it) }
    }
}

