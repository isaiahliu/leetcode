package p15xx

import util.expect

fun main() {
    class Solution {
        fun maxNonOverlapping(nums: IntArray, target: Int): Int {
            var result = 0

            val set = hashSetOf(0)

            var sum = 0
            nums.forEach {
                sum += it
                if (sum - target in set) {
                    result++
                    set.clear()
                    set.add(0)
                    sum = 0
                } else {
                    set.add(sum)
                }
            }

            return result
        }
    }

    expect {
        Solution().maxNonOverlapping(
            intArrayOf(), 1
        )
    }
}

