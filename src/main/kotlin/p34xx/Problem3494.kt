package p34xx

import util.expect

fun main() {
    class Solution {
        fun minTime(skill: IntArray, mana: IntArray): Long {
            var offset = 0L
            var sum = 0L

            val sums = LongArray(skill.size) {
                sum += skill[it] * mana[0]
                sum
            }

            for (manaIndex in 1 until mana.size) {
                sum = 0L
                var newOffset = 0L

                skill.forEachIndexed { skillIndex, s ->
                    newOffset = maxOf(newOffset, sums[skillIndex] + offset - sum)
                    sum += s * mana[manaIndex]
                    sums[skillIndex] = sum
                }

                offset = newOffset
            }

            return sum + offset
        }
    }

    expect {
        Solution().minTime(
            intArrayOf(1, 5, 2, 4), intArrayOf(5, 1, 4, 2)
        )
    }
}
