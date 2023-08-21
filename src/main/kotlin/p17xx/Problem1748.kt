package p17xx

import util.expect

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

    expect {
        Solution().sumOfUnique(
            intArrayOf()
        )
    }
}
