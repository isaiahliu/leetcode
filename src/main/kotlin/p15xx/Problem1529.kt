package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minFlips(target: String): Int {
            var pre = '0'

            var result = 0
            target.forEach {
                if (pre != it) {
                    result++
                    pre = '0' + ('1' - pre)
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minFlips(
            "10111"
        ).also { println(it) }
    }
}

