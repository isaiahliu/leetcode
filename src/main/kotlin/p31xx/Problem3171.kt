package p31xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun minimumDifference(nums: IntArray, k: Int): Int {
            var current = setOf(nums[0])

            val pre = Array(nums.size) {
                val num = nums[it]
                current = buildSet {
                    current.forEach {
                        add(it or num)
                    }
                    add(num)
                }
                current
            }

            var result = Int.MAX_VALUE
            current = setOf(nums[nums.lastIndex])

            for (i in nums.lastIndex downTo 0) {
                val num = nums[i]

                current = buildSet {
                    current.forEach {
                        add(it or num)
                    }
                    add(num)
                }

                current.forEach { c ->
                    pre[i].forEach { p ->
                        result = minOf(result, ((c or p) - k).absoluteValue)
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumDifference(
            intArrayOf(7, 4), 2
        )
    }
}
