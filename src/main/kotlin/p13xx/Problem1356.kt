package p13xx

import util.expect

fun main() {
    class Solution {
        fun sortByBits(arr: IntArray): IntArray {
            return arr.sortedWith(compareBy<Int> { Integer.bitCount(it) }.thenBy { it }).toIntArray()
        }
    }

    expect {
        Solution().sortByBits(
            intArrayOf(1, 1000000000)
        )
    }
}

