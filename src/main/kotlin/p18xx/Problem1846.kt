package p18xx

import util.expect

fun main() {
    class Solution {
        fun maximumElementAfterDecrementingAndRearranging(arr: IntArray): Int {
            arr.sort()

            arr[0] = 1
            for (index in 1 until arr.size) {
                arr[index] = arr[index].coerceAtMost(arr[index - 1] + 1)
            }
            return arr[arr.lastIndex]
        }
    }

    expect {
        Solution().maximumElementAfterDecrementingAndRearranging(
            intArrayOf(2, 2, 1, 2, 1)
        )

    }
}
