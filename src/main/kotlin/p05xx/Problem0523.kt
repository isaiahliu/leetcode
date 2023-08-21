package p05xx

import util.expect

fun main() {
    class Solution {
        fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
            var rem = 0

            val remMap = hashMapOf(0 to -1)

            nums.forEachIndexed { index, n ->
                rem += n
                rem %= k

                remMap[rem]?.also {
                    if (index - it > 1) {
                        return true
                    }
                } ?: run { remMap[rem] = index }
            }

            return false
        }
    }

    expect {
        Solution().checkSubarraySum(intArrayOf(1, 0, 0, 2), 100)
    }
}