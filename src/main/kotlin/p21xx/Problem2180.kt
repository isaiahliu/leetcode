package p21xx

import util.expect

fun main() {
    class Solution {
        fun countEven(num: Int): Int {
            var even = 1
            var result = 0
            for (i in 1..num) {
                when {
                    i % 1000 == 0 -> {}
                    i % 100 == 0 -> {
                        even = even xor 1
                    }

                    i % 10 == 0 -> {}
                    else -> {
                        even = even xor 1
                    }
                }

                result += even
            }

            return result
        }
    }

    expect {
        Solution().countEven(
            4
        )
    }
}