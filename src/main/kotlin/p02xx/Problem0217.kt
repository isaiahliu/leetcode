package p02xx

import util.expect

fun main() {
    class Solution {
        fun containsDuplicate(nums: IntArray): Boolean {
            val set = hashSetOf<Int>()

            nums.forEach {
                if (!set.add(it)) {
                    return true
                }
            }

            return false
        }
    }

    expect {
        Solution().containsDuplicate(
            intArrayOf()
        )
    }
}

