package p02xx

fun main() {
    class Solution {
        fun minSubArrayLen(target: Int, nums: IntArray): Int {
            var leftIndex = 0

            var sum = nums[leftIndex]
            if (sum >= target) {
                return 1
            }

            var maxLength = Int.MAX_VALUE
            var rightIndex = leftIndex

            while (rightIndex < nums.size) {
                if (sum < target && rightIndex - leftIndex + 1 < maxLength) {
                    rightIndex++
                    sum += nums.getOrNull(rightIndex) ?: 0
                } else {
                    if (sum >= target) {
                        maxLength = maxLength.coerceAtMost(rightIndex - leftIndex + 1)

                        if (maxLength == 1) {
                            return 1
                        }
                    }

                    sum -= nums[leftIndex++]
                }
            }

            return if (maxLength == Int.MAX_VALUE) {
                0
            } else {
                maxLength
            }
        }
    }

    println(
        Solution().minSubArrayLen(
            7, intArrayOf(2, 3, 1, 2, 4, 3)
        )
    )
}

