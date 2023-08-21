package p15xx

import util.expect

fun main() {
    class Solution {
        fun containsPattern(arr: IntArray, m: Int, k: Int): Boolean {
            loop@ for (i in 0..arr.size - m * k) {
                for (pos in 0 until m) {
                    val set = hashSetOf<Int>()
                    repeat(k) { r ->
                        set.add(arr[i + r * m + pos])
                    }

                    if (set.size > 1) {
                        continue@loop
                    }
                }

                return true
            }

            return false
        }
    }

    expect {
        Solution().containsPattern(
            intArrayOf(1, 4, 4, 4), 1, 3
        )
    }
}

