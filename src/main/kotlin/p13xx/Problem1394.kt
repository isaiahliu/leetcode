package p13xx

import util.expect

fun main() {
    class Solution {
        fun findLucky(arr: IntArray): Int {
            return arr.toList().groupingBy { it }.eachCount().filter { it.key == it.value }.keys.maxOrNull() ?: -1
        }
    }

    expect {
        Solution().findLucky(
            intArrayOf(4, 1, 3),
        )
    }
}

