package p20xx

import util.expect

fun main() {
    class Solution {
        fun minimizedMaximum(n: Int, quantities: IntArray): Int {
            var min = 1
            var max = quantities.max()

            var result = 0
            while (min <= max) {
                val mid = (min + max) / 2

                val sum = quantities.map {
                    it / mid + if (it % mid > 0) 1 else 0
                }.sum()

                if (sum <= n) {
                    result = mid
                    max = mid - 1
                } else {
                    min = mid + 1
                }
            }

            return result
        }
    }

    expect {
        Solution().minimizedMaximum(
            1, intArrayOf()
        )
    }
}