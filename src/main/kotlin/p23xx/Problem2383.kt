package p23xx

import util.expect

fun main() {
    class Solution {
        fun minNumberOfHours(initialEnergy: Int, initialExperience: Int, energy: IntArray, experience: IntArray): Int {
            var result = (energy.sum() + 1 - initialEnergy).coerceAtLeast(0)

            var e = initialExperience
            experience.forEach {
                (it - e + 1).takeIf { it > 0 }?.also {
                    result += it
                    e += it
                }

                e += it
            }

            return result
        }
    }

    expect {
        Solution().minNumberOfHours(5, 3, intArrayOf(1, 4, 3, 2), intArrayOf(2, 6, 3, 1))
    }
}