package p34xx

import util.expect

fun main() {
    class Solution {
        fun minTime(skill: IntArray, mana: IntArray): Long {
            var offset = 0L

            var sum = 0L
            mana.forEachIndexed { manaIndex, m ->
                sum = 0L
                var newOffset = 0L
                var lastSum = 0L

                skill.forEach { s ->
                    mana.getOrNull(manaIndex - 1)?.also { lastM ->
                        lastSum += lastM * s
                        newOffset = maxOf(newOffset, lastSum + offset - sum)
                    }

                    sum += s * m
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
