package p18xx

import util.expect

fun main() {
    class Solution {
        fun getXORSum(arr1: IntArray, arr2: IntArray): Int {
            return arr1.fold(0) { a, b -> a xor b } and arr2.fold(0) { a, b -> a xor b }
        }
    }

    expect {
        Solution().getXORSum(
            intArrayOf(1, 2, 3), intArrayOf(6, 5)
        )

    }
}
