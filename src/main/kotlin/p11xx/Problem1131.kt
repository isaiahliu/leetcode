package p11xx

import util.expect

fun main() {
    class Solution {
        fun maxAbsValExpr(arr1: IntArray, arr2: IntArray): Int {
            var max1: Int = Int.MIN_VALUE
            var max2: Int = Int.MIN_VALUE
            var max3: Int = Int.MIN_VALUE
            var max4: Int = Int.MIN_VALUE
            var min1: Int = Int.MAX_VALUE
            var min2: Int = Int.MAX_VALUE
            var min3: Int = Int.MAX_VALUE
            var min4: Int = Int.MAX_VALUE

            for (i in arr1.indices) {
                max1 = max1.coerceAtLeast(arr1[i] + arr2[i] + i)
                max2 = max2.coerceAtLeast(-arr1[i] + arr2[i] + i)
                max3 = max3.coerceAtLeast(arr1[i] - arr2[i] + i)
                max4 = max4.coerceAtLeast(arr1[i] + arr2[i] - i)
                min1 = min1.coerceAtMost(arr1[i] + arr2[i] + i)
                min2 = min2.coerceAtMost(-arr1[i] + arr2[i] + i)
                min3 = min3.coerceAtMost(arr1[i] - arr2[i] + i)
                min4 = min4.coerceAtMost(arr1[i] + arr2[i] - i)
            }
            return (max1 - min1).coerceAtLeast(max2 - min2).coerceAtLeast((max3 - min3).coerceAtLeast(max4 - min4))
        }
    }

    expect {
        Solution().maxAbsValExpr(
            intArrayOf(), intArrayOf()
        )

    }
}