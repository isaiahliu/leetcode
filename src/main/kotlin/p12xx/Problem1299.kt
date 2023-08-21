package p12xx

import util.expect

fun main() {
    class Solution {
        fun replaceElements(arr: IntArray): IntArray {
            var max = -1

            for (i in arr.lastIndex downTo 0) {
                val cur = arr[i]

                arr[i] = max

                max = max.coerceAtLeast(cur)
            }

            return arr
        }
    }

    expect {
        Solution().replaceElements(
            intArrayOf()
        )
    }
}
