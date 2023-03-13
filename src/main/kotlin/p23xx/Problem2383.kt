package p23xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().minNumberOfHours(5, 3, intArrayOf(1, 4, 3, 2), intArrayOf(2, 6, 3, 1)).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}