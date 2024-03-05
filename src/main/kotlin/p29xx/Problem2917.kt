package p29xx

import util.expect

fun main() {
    class Solution {
        fun findKOr(nums: IntArray, k: Int): Int {
            val counts = IntArray(31)

            nums.forEach {
                var t = it
                var index = 0

                while (t > 0) {
                    if (t and 1 > 0) {
                        counts[index]++
                    }

                    t /= 2
                    index++
                }
            }

            return counts.mapIndexed { index, c ->
                if (c >= k) {
                    1 shl index
                } else {
                    0
                }
            }.sum()
        }
    }

    expect {
        Solution().findKOr(
            intArrayOf(7, 12, 9, 8, 9, 15), 4
        )
    }
}
