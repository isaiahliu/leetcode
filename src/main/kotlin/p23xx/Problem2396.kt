package p23xx

import util.expect

fun main() {
    class Solution {
        fun isStrictlyPalindromic(n: Int): Boolean {
            (2..n - 2).forEach {
                val nums = arrayListOf<Int>()

                var t = n
                while (t > 0) {
                    nums.add(t % it)
                    t /= it
                }

                var l = 0
                var r = nums.lastIndex

                while (l < r) {
                    if (nums[l++] != nums[r--]) {
                        return false
                    }
                }
            }

            return true
        }
    }

    expect {
        Solution().isStrictlyPalindromic(
            5
        )
    }
}