package p09xx

import util.expect

fun main() {
    class Solution {
        fun sortArrayByParity(nums: IntArray): IntArray {
            var l = 0
            var r = nums.lastIndex

            fun moveL() {
                while (l < nums.size && nums[l] % 2 == 0) {
                    l++
                }
            }

            fun moveR() {
                while (r >= 0 && nums[r] % 2 == 1) {
                    r--
                }
            }

            moveL()
            moveR()

            while (l < r) {
                val t = nums[l]
                nums[l] = nums[r]
                nums[r] = t

                moveL()
                moveR()
            }

            return nums
        }
    }

    expect {
        Solution().sortArrayByParity(
            intArrayOf()
        )
    }
}