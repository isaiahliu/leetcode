fun main() {
    class Solution {
        fun firstMissingPositive(nums: IntArray): Int {
            val range = 1..nums.size

            var hasOne = false
            for (i in nums.indices) {
                if (nums[i] !in range) {
                    nums[i] = 0
                }

                if (nums[i] == 1) {
                    hasOne = true
                }
            }

            if (!hasOne) {
                return 1
            }

            val flag = nums.size + 1

            for (i in nums.indices) {
                val num = nums[i]
                when {
                    num == i + 1 -> {
                        nums[i] = flag
                    }

                    num < 0 -> {
                        when {
                            nums[0 - num - 1] in range -> {
                                nums[0 - num - 1] *= -1
                            }

                            0 - nums[0 - num - 1] in range -> {
                            }

                            else -> {
                                nums[0 - num - 1] = flag
                            }
                        }

                        nums[i] = flag
                    }

                    num == flag -> {}

                    num > 0 -> {
                        when {
                            nums[num - 1] in range -> {
                                nums[num - 1] *= -1
                            }

                            0 - nums[num - 1] in range -> {
                            }

                            else -> {
                                nums[num - 1] = flag
                            }
                        }

                        nums[i] = 0
                    }
                }
            }

            for (i in nums.indices) {
                if (nums[i] != flag) {
                    return i + 1
                }
            }

            return nums.size + 1
        }
    }

    println(Solution().firstMissingPositive(intArrayOf(3, 3, 1, 4, 0)))
}

