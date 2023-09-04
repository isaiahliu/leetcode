package p22xx

import util.expect

fun main() {
    class Solution {
        fun largestCombination(candidates: IntArray): Int {
            return (0 until 25).maxOf {
                val p = 1 shl it
                candidates.count { it and p > 0 }
            }
        }
    }

    expect(3) {
        Solution().largestCombination(
            intArrayOf()
        )
    }
}