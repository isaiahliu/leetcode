package p25xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun beautifulSubsets(nums: IntArray, k: Int): Int {
            val stack = LinkedList<Boolean>()

            repeat(nums.size) { stack.push(false) }
            val counts = IntArray(1001)

            var result = 0
            while (stack.isNotEmpty()) {
                when {
                    stack.poll() -> {
                        counts[nums[stack.size]]--
                    }

                    counts.getOrElse(nums[stack.size] + k) { 0 } == 0 && counts.getOrElse(nums[stack.size] - k) { 0 } == 0 -> {
                        result++
                        counts[nums[stack.size]]++
                        stack.push(true)

                        while (stack.size < nums.size) {
                            stack.push(false)
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().beautifulSubsets(intArrayOf(2, 4, 6), 2)
    }
}