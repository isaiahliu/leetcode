package p02xx

import util.expect

fun main() {
    class Solution {
        fun countPrimes(n: Int): Int {
            val flags = BooleanArray(n)

            var result = 0
            for (i in 2 until n) {
                if (!flags[i]) {
                    result++

                    var index = i * 2
                    while (index < flags.size) {
                        flags[index] = true
                        index += i
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().countPrimes(
            10
        )
    }
}

