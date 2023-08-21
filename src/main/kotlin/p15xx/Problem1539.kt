package p15xx

import util.expect

fun main() {
    class Solution {
        fun findKthPositive(arr: IntArray, k: Int): Int {
            var num = 1
            var found = 0

            arr.forEach {
                while (num < it) {
                    found++

                    if (found == k) {
                        return num
                    }
                    num++
                }

                num = it + 1
            }

            return num + k - found - 1
        }
    }

    expect {
        Solution().findKthPositive(
            intArrayOf(1, 2, 3, 4), 2
        )
    }
}

