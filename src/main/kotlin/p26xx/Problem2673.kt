package p26xx

import util.expect

fun main() {
    class Solution {
        fun minIncrements(n: Int, cost: IntArray): Int {
            val sums = IntArray(n + 1)

            val childrenMax = IntArray(n + 1)

            var max = 0
            cost.forEachIndexed { i, c ->
                val index = i + 1
                sums[index] = c + sums[index / 2]
                childrenMax[index] = sums[index]
                max = maxOf(max, sums[index])
                var t = index / 2
                while (t > 0) {
                    childrenMax[t] = maxOf(childrenMax[t], sums[index])
                    t /= 2
                }
            }

            val add = IntArray(n + 1)

            var result = 0
            for (index in 1..n) {
                val nodeMax = childrenMax[index] + add[index / 2]

                val diff = max - nodeMax
                add[index] = add[index / 2] + diff
                result += diff
            }
            return result
        }
    }

    expect {
        Solution().minIncrements(
            7, intArrayOf(1, 5, 2, 2, 3, 3, 1)
        )
    }
}
