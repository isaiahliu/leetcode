package p34xx

import util.expect

fun main() {
    class Solution {
        fun minTime(skill: IntArray, mana: IntArray): Long {
            val sums = LongArray(skill.size + 1)

            return mana.indices.fold(0L) { offset, manaIndex ->
                skill.indices.maxOf { skillIndex ->
                    (sums[skillIndex + 1] + offset - sums[skillIndex]).also {
                        sums[skillIndex + 1] = sums[skillIndex] + skill[skillIndex] * mana[manaIndex]
                    }
                }
            } + sums.last()
        }
    }

    expect {
        Solution().minTime(
            intArrayOf(1, 5, 2, 4), intArrayOf(5, 1, 4, 2)
        )
    }
}
