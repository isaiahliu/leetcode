package p25xx

import util.expect

fun main() {
    class Solution {
        fun isReachable(targetX: Int, targetY: Int): Boolean {
            return gcd(targetX, targetY).let { it and (it - 1) == 0 }
        }

        private fun gcd(a: Int, b: Int): Int {
            var a = a
            var b = b
            while (a != 0) {
                val tmp = a
                a = b % a
                b = tmp
            }
            return b
        }
    }

    expect {
        Solution().isReachable(
            339513622, 655934895
        )
    }
}

