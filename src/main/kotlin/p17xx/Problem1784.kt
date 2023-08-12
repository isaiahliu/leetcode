package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkOnesSegment(s: String): Boolean {
            var status = s[0] - '0'
            s.forEach {
                status += (status xor (it - '0')) and 1

                if (status == 3) {
                    return false
                }
            }
            return true
        }
    }

    measureTimeMillis {
        Solution().checkOnesSegment(
            "11001"
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
