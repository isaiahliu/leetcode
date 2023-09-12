package p24xx

import util.expect

fun main() {
    class Solution {
        fun findArray(pref: IntArray): IntArray {
            var sum = 0
            return IntArray(pref.size) {
                (sum xor pref[it]).also {
                    sum = sum xor it
                }
            }
        }
    }

    expect {
        Solution().findArray(
            intArrayOf(5, 2, 0, 3, 1)
        ).toList()
    }
}