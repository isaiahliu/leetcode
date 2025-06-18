package p29xx

import util.expect

fun main() {
    class Solution {
        fun divideArray(nums: IntArray, k: Int): Array<IntArray> {
            nums.sort()

            return Array(nums.size / 3) {
                intArrayOf(nums[it * 3], nums[it * 3 + 1], nums[it * 3 + 2]).also {
                    if (it[2] - it[0] > k) {
                        return emptyArray()
                    }
                }
            }
        }
    }

    expect {
        Solution().divideArray(
            intArrayOf(), 1
        )
    }
}
