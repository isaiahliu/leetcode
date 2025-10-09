package p34xx

import util.expect

fun main() {
    class Solution {
        fun minTime(skill: IntArray, mana: IntArray): Long {
            var sum = 0L
            var dp = LongArray(skill.size) {
                sum += skill[it] * mana[0]
                sum
            }

            for (i in 1 until mana.size) {

                sum = 0L
                var offset = 0L
                val newDp = LongArray(skill.size) {
                    offset = maxOf(offset, dp[it] - sum)

                    sum += skill[it] * mana[i]
                    sum
                }

                newDp.forEachIndexed { index, v ->
                    newDp[index] = v + offset
                }

                dp = newDp
            }

            return dp.last()
        }
    }

    expect {
        Solution().minTime(
            intArrayOf(1, 5, 2, 4), intArrayOf(5, 1, 4, 2)
        )
    }
}
