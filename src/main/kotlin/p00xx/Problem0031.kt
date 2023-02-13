package p00xx

fun main() {
    class Solution {
        fun nextPermutation(nums: IntArray): Unit {
            var index1 = -1
            var index2 = -1
            for (index in 1 until nums.size) {
                if (nums[index - 1] < nums[index]) {
                    index1 = index - 1
                    index2 = index
                } else if (index1 >= 0) {
                    nums[index].takeIf { it > nums[index1] && it < nums[index2] }?.also {
                        index2 = index
                    }
                }
            }

            if (index1 >= 0) {
                val t = nums[index1]
                nums[index1] = nums[index2]
                nums[index2] = t

                nums.sort(index1 + 1, nums.size)
            } else {
                nums.sort()
            }
        }
    }
    Solution().nextPermutation(intArrayOf(1, 3, 2))
}

