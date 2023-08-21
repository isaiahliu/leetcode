package p00xx

import util.expect

fun main() {
    class Solution {
        fun twoSum(nums: IntArray, target: Int): IntArray {
            val map = hashMapOf<Int, Int>()

            nums.forEachIndexed { index, num ->
                map[target - num]?.also {
                    return intArrayOf(index, it)
                } ?: run { map[num] = index }
            }

            return intArrayOf()
        }
    }

    expect {
        Solution().twoSum(intArrayOf(2, 7, 11, 15), 9).joinToString()
    }
}

