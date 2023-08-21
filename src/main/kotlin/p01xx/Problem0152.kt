package p01xx

import util.expect

fun main() {
    class Solution {
        fun maxProduct(nums: IntArray): Int {
            var max = nums[0]

            var startIndex = 0

            while (true) {
                while (startIndex < nums.size && nums[startIndex] == 0) {
                    max = max.coerceAtLeast(0)

                    startIndex++
                }

                if (startIndex == nums.size) {
                    break
                }

                var endIndex = startIndex

                var prod = nums[startIndex]
                max = max.coerceAtLeast(prod)

                while (endIndex + 1 < nums.size && nums[endIndex + 1] != 0) {
                    endIndex++

                    prod *= nums[endIndex]
                    max = max.coerceAtLeast(prod)
                }

                if (prod < 0) {
                    prod = nums[endIndex]
                    max = max.coerceAtLeast(prod)

                    for (i in endIndex - 1 downTo startIndex) {
                        prod *= nums[i]
                        max = max.coerceAtLeast(prod)
                    }
                }

                startIndex = endIndex + 1
            }
            return max
        }
    }

    expect {
        Solution().maxProduct(
            intArrayOf(0, 2)
        )
    }
}

