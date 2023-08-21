package p11xx

import util.expect

fun main() {
    class Solution {
        fun distributeCandies(candies: Int, num_people: Int): IntArray {
            val result = IntArray(num_people)

            var index = 0
            var count = 1
            var remaining = candies

            while (remaining > 0) {
                result[index++ % num_people] += count.coerceAtMost(remaining)

                remaining -= count++
            }

            return result
        }
    }

    expect {
        Solution().distributeCandies(
            1, 3
        )

    }
}