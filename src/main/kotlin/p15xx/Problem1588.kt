package p15xx

import util.expect

fun main() {
    class Solution {
        fun sumOddLengthSubarrays(arr: IntArray): Int {
            for (i in 1 until arr.size) {
                arr[i] += arr[i - 1]
            }

            var result = 0
            for (i in arr.indices) {
                for (j in i until arr.size step 2) {
                    result += arr[j] - (arr.getOrNull(i - 1) ?: 0)
                }
            }

            return result
        }
    }

    expect {
        Solution().sumOddLengthSubarrays(
            intArrayOf()
        )
    }
}

