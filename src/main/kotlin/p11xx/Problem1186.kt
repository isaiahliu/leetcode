package p11xx

import util.expect

fun main() {
    class Solution {
        fun maximumSum(arr: IntArray): Int {
            var sum1 = arr[0]
            var sum2 = 0
            var result = arr[0]
            for (i in 1 until arr.size) {
                sum2 = sum1.coerceAtLeast(sum2 + arr[i])
                sum1 = sum1.coerceAtLeast(0) + arr[i]
                result = result.coerceAtLeast(sum1).coerceAtLeast(sum2)
            }

            return result
        }
    }

    expect {
        Solution().maximumSum(intArrayOf(1, -2, 0, 3))
    }
}

