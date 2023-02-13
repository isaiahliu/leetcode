package p00xx

fun main() {
    class Solution {
        fun twoSum(nums: IntArray, target: Int): IntArray {
            for (i in nums.indices) {
                for (j in i + 1 until nums.size) {
                    if (nums[i] + nums[j] == target) {
                        return intArrayOf(i, j)
                    }
                }
            }

            return intArrayOf()
        }
    }

    println(Solution().twoSum(intArrayOf(2, 7, 11, 15), 9).joinToString())
}

