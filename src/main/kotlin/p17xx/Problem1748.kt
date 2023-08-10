package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sumOfUnique(nums: IntArray): Int {
            val visited = hashSetOf<Int>()
            val addOnce = hashSetOf<Int>()

            var result = 0

            nums.forEach {
                when {
                    visited.add(it) -> {
                        addOnce.add(it)
                        result += it
                    }

                    addOnce.remove(it) -> {
                        result -= it
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().sumOfUnique(
            intArrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
