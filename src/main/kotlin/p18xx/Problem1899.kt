package p18xx

import util.expect

fun main() {
    class Solution {
        fun mergeTriplets(triplets: Array<IntArray>, target: IntArray): Boolean {
            val (ta, tb, tc) = target
            return triplets.fold(intArrayOf(0, 0, 0)) { sum, (a, b, c) ->
                if (a <= ta && b <= tb && c <= tc) {
                    sum[0] = sum[0].coerceAtLeast(a)
                    sum[1] = sum[1].coerceAtLeast(b)
                    sum[2] = sum[2].coerceAtLeast(c)
                }

                sum
            }.foldIndexed(true) { index, acc, num ->
                acc and (target[index] == num)
            }
        }
    }

    expect {
        Solution().mergeTriplets(
            arrayOf(), intArrayOf()
        )
    }
}
