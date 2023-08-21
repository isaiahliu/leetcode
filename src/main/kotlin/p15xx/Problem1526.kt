package p15xx

import util.expect

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

    expect {
        Solution().minNumberOperations(
            intArrayOf()
        )
    }
}

