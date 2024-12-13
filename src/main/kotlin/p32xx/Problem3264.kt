package p32xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun getFinalState(nums: IntArray, k: Int, multiplier: Int): IntArray {
            val indices = PriorityQueue(compareBy<Int> { nums[it] }.thenBy { it })
            indices.addAll(nums.indices)

            repeat(k) {
                val index = indices.poll()

                nums[index] *= multiplier

                indices.add(index)
            }

            return nums
        }
    }
    expect {
        Solution().getFinalState(
            intArrayOf(), 1, 1
        )
    }
}
