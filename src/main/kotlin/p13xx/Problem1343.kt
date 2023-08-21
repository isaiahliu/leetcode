package p13xx

import util.expect

fun main() {
    class Solution {
        fun numOfSubarrays(arr: IntArray, k: Int, threshold: Int): Int {
            var sum = (0 until k).map { arr[it] }.sum()

            var result = 0
            if (sum >= k * threshold) {
                result++
            }

            for (i in k until arr.size) {
                sum += arr[i] - arr[i - k]

                if (sum >= k * threshold) {
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().numOfSubarrays(
            intArrayOf(), 1, 2
        )
    }
}

