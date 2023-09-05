package p22xx

import util.expect

fun main() {
    class Solution {
        fun arrayChange(nums: IntArray, operations: Array<IntArray>): IntArray {
            val map1 = hashMapOf<Int, Int>()
            val map2 = hashMapOf<Int, Int>()

            operations.forEach { (from, to) ->
                val f = map2.remove(from) ?: from

                map1[f] = to
                map2[to] = f
            }

            return nums.map {
                map1[it] ?: it
            }.toIntArray()
        }
    }

    expect {
        Solution().arrayChange(
            intArrayOf(1, 3, 5, 2, 4, 8, 2, 2), arrayOf()
        )
    }
}