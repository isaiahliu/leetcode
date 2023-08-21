package p11xx

import util.expect

fun main() {
    class Solution {
        fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
            val map = arr2.mapIndexed { index, num -> num to index }.toMap()

            return arr1.sortedWith(compareBy<Int> { map[it] ?: arr2.size }.thenBy { it }).toIntArray()
        }
    }

    expect {
        Solution().relativeSortArray(
            intArrayOf(), intArrayOf()
        )

    }
}