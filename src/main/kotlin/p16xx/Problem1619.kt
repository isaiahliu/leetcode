package p16xx

import util.expect

fun main() {
    class Solution {
        fun trimMean(arr: IntArray): Double {
            return arr.sorted().slice(arr.size / 20 until arr.size - arr.size / 20).average()
        }
    }

    expect {
        Solution().trimMean(
            intArrayOf()
        )
    }
}