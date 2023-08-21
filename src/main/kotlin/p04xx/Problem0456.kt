package p04xx

import util.expect

fun main() {
    class Solution {
        fun find132pattern(nums: IntArray): Boolean {
            val ranges = arrayListOf<IntRange>()

            var rangeMax = nums[0]

            var min = nums[0]
            var pre = min

            var inc = true

            for (num in nums) {
                when {
                    inc && num < pre -> {
                        if (pre >= rangeMax) {
                            rangeMax = pre
                            ranges.clear()
                        }

                        ranges.add(min..pre)

                        inc = false
                    }

                    !inc && num > pre -> {
                        min = pre

                        inc = true
                    }
                }

                if (ranges.any { num > it.first && num < it.last }) {
                    return true
                }

                pre = num
            }

            return false
        }
    }

    expect {
        Solution().find132pattern(intArrayOf(3, 1, 4, 2))
    }
}