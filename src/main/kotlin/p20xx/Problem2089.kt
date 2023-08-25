package p20xx

import util.expect

fun main() {
    class Solution {
        fun targetIndices(nums: IntArray, target: Int): List<Int> {
            var count = 0
            var lessCount = 0
            nums.forEach {
                when {
                    it < target -> {
                        lessCount++
                    }

                    it == target -> {
                        count++
                    }
                }
            }

            return List(count) { lessCount + it }
        }
    }

    expect(13) {
        Solution().targetIndices(
            intArrayOf(), 1
        )
    }
}