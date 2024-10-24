package p31xx

import util.expect

fun main() {
    class Solution {
        fun maxTotalReward(rewardValues: IntArray): Int {
            val scores = hashSetOf(0)

            rewardValues.sort()

            rewardValues.forEach { r ->
                scores.addAll(scores.filter { it < r }.map { it + r })
            }

            return scores.max()
        }
    }

    expect {
        Solution().maxTotalReward(
            intArrayOf()
        )
    }
}
