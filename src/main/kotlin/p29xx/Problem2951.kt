package p29xx

import util.expect

fun main() {
    class Solution {
        fun findPeaks(mountain: IntArray): List<Int> {
            return (1 until mountain.lastIndex).filter { mountain[it] > mountain[it - 1] && mountain[it] > mountain[it + 1] }
        }
    }

    expect {
        Solution().findPeaks(
            intArrayOf(7, 10, 2)
        )
    }
}
