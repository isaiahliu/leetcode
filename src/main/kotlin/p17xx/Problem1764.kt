package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canChoose(groups: Array<IntArray>, nums: IntArray): Boolean {
            var numIndex = 0

            for (group in groups) {
                loop@ while (true) {
                    for ((index, num) in group.withIndex()) {
                        if (num != (nums.getOrNull(numIndex + index) ?: return false)) {
                            numIndex++
                            continue@loop
                        }
                    }

                    numIndex += group.size

                    break
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().canChoose(
            arrayOf(), intArrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
