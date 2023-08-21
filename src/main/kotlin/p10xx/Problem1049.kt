package p10xx

import util.expect

fun main() {
    class Solution {
        fun lastStoneWeightII(stones: IntArray): Int {
            stones.sort()

            val sum = stones.sum()

            val set = hashSetOf(0)

            for (stone in stones) {
                if (stone + stone > sum) {
                    break
                }

                set.addAll(set.map { it + stone }.filter { it + it <= sum })
            }

            val best = set.max()

            return sum - best * 2
        }
    }

    expect {
        Solution().lastStoneWeightII(
            intArrayOf(2, 7, 4, 1, 8, 1)
        )
    }
}