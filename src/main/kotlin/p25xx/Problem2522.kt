package p25xx

import util.expect

fun main() {
    class Solution {
        fun minimumPartition(s: String, k: Int): Int {
            var result = 1
            var sum = 0L
            s.forEach {
                val n = it - '0'

                when {
                    sum * 10 + n <= k -> {
                        sum = sum * 10 + n
                    }

                    n <= k -> {
                        sum = n.toLong()
                        result++
                    }

                    else -> return -1
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumPartition(
            "", 1
        )
    }
}