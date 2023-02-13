package p00xx

fun main() {
    class Solution {
        fun removeElement(nums: IntArray, `val`: Int): Int {
            var processIndex = 0
            var watchIndex = 0

            while (watchIndex < nums.size) {
                val num = nums[watchIndex++]

                if (num != `val`) {
                    nums[processIndex++] = num
                }
            }

            return processIndex
        }
    }

}

