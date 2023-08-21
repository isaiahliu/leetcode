package p15xx

import util.expect

fun main() {
    class Solution {
        fun canMakeArithmeticProgression(arr: IntArray): Boolean {
            arr.sort()

            val delta = arr[1] - arr[0]

            for (i in 2 until arr.size) {
                if (arr[i] - arr[i - 1] != delta) {
                    return false
                }
            }

            return true
        }
    }

    expect {
        Solution().canMakeArithmeticProgression(
            intArrayOf(-19, -12)
        )
    }
}

