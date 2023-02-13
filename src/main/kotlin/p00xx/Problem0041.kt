package p00xx

fun main() {
    class Solution {
        fun firstMissingPositive(nums: IntArray): Int {
            val range = 1..nums.size

            for (i in nums.indices) {
                if (nums[i] !in range) {
                    nums[i] = 0
                }
            }

            val max = nums.size + 1

            for (i in nums.indices) {
                val num = nums[i] % max
                if (num in range) {
                    nums[num - 1] = nums[num - 1] % max + max

                    nums[i] -= num
                }
            }

            for (i in nums.indices) {
                if (nums[i] != max) {
                    return i + 1
                }
            }

            return max
        }
    }

    println(Solution().firstMissingPositive(intArrayOf(3, 3, 1, 4, 0)))
}

