package p06xx

import util.expect

fun main() {
    class Solution {
        fun findErrorNums(nums: IntArray): IntArray {
            var index = 0

            var errorNum = 0
            var errorPos = 0
            while (index < nums.size) {
                if (index + 1 == nums[index]) {
                    index++
                } else {
                    val t = nums[index]

                    if (t == nums[t - 1]) {
                        errorNum = t

                        if (index + 1 != errorNum) {
                            errorPos = index + 1
                        }

                        index++
                        continue
                    }
                    nums[index] = nums[t - 1]
                    nums[t - 1] = t
                }
            }

            return intArrayOf(errorNum, errorPos)
        }
    }

    expect {
        Solution().findErrorNums(
            intArrayOf(1, 2, 2, 3, 4, 5, 6)
        ).toList()
    }
}