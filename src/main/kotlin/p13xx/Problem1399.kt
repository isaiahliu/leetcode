package p13xx

import util.expect

fun main() {
    class Solution {
        fun countLargestGroup(n: Int): Int {
            return (1..n).map {
                var t = it
                var result = 0
                while (t > 0) {
                    result += t % 10
                    t /= 10
                }

                result
            }.groupingBy { it }.eachCount().values.let {
                val max = it.max()

                it.count { it == max }
            }
        }
    }

    expect {
        Solution().countLargestGroup(
            10
        )
    }
}

