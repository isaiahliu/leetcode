package p08xx

import util.expect

fun main() {
    class Solution {
        fun numFactoredBinaryTrees(arr: IntArray): Int {
            val m = 1000000007
            arr.sort()

            val results = hashMapOf<Int, Long>()

            arr.forEach { num ->
                var result = 1L

                for (left in arr) {
                    if (left * left > num) {
                        break
                    }

                    val right = num / left

                    if (left * right == num && right in results) {
                        val c = if (left == right) 1 else 2

                        result += (results[left] ?: 0L) * (results[right] ?: 0L) * c % m
                    }
                }

                results[num] = result % m
            }

            return (results.values.sum() % m).toInt()
        }
    }

    expect {
        Solution().numFactoredBinaryTrees(
            intArrayOf()
        )
    }
}