fun main() {
    class Solution {
        fun removeDuplicates(nums: IntArray): Int {
            if (nums.isEmpty()) {
                return 0
            }

            var current = nums.getOrNull(0) ?: return 0
            current--

            var processIndex = 0
            var watchIndex = 0

            while (watchIndex < nums.size) {
                val num = nums[watchIndex++]

                if (num != current) {
                    nums[processIndex++] = num
                    current = num
                }
            }

            return processIndex
        }
    }

    Solution().removeDuplicates(intArrayOf(1, 1, 2))
}

