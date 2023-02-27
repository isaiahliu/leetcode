package p02xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canWinNim(n: Int): Boolean {
            return n % 4 != 0
        }
    }

    measureTimeMillis {
        Solution().canWinNim(4).also { println(it) }
    }
}

