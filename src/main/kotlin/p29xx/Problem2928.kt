package p29xx

import util.expect

fun main() {
    class Solution {
        fun distributeCandies(n: Int, limit: Int): Int {
            fun distribute(person: Int, num: Int): Int {
                return when {
                    person > 1 -> {
                        (0..minOf(num, limit)).sumOf { distribute(person - 1, num - it) }
                    }

                    num <= limit -> 1
                    else -> 0
                }
            }

            return distribute(3, n)
        }
    }

    expect {
        Solution().distributeCandies(
            5, 4
        )
    }
}
