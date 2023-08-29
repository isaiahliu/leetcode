package p21xx

import util.expect

fun main() {
    class Solution {
        fun minSwaps(nums: IntArray): Int {
            val oneCount = nums.count { it == 1 }

            if (oneCount == 0) {
                return 0
            }

            var count = 0
            repeat(oneCount) {
                if (nums[it] == 1) {
                    count++
                }
            }

            var result = oneCount - count

            var index = oneCount

            repeat(nums.size) {
                if (nums[index % nums.size] == 1) {
                    count++
                }

                if (nums[(index - oneCount).mod(nums.size)] == 1) {
                    count--
                }

                result = result.coerceAtMost(oneCount - count)

                index++
            }

            return result
        }
    }

    expect {
        Solution().minSwaps(
            intArrayOf(0, 1, 0, 1, 1, 0, 0)
        )
    }
}