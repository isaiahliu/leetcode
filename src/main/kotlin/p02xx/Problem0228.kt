package p02xx

fun main() {
    class Solution {
        fun summaryRanges(nums: IntArray): List<String> {
            if (nums.isEmpty()) {
                return emptyList()
            }

            var min = nums[0]
            var max = nums[0]

            val result = arrayListOf<String>()

            fun pushResult() {
                if (max > min) {
                    result.add("${min}->${max}")
                } else {
                    result.add("${min}")
                }
            }

            for (index in 1 until nums.size) {
                val num = nums[index]

                if (num == max + 1) {
                    max++
                } else {
                    pushResult()

                    min = num
                    max = num
                }
            }

            pushResult()

            return result
        }
    }

    println(
        Solution().summaryRanges(
            intArrayOf(0, 2, 3, 4, 6, 8, 9)
        )
    )
}

