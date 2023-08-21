package p17xx

import util.expect

fun main() {
    class Solution {
        fun countPairs(deliciousness: IntArray): Int {
            var result = 0L
            val m = 1000000007

            val mealSet = List(22) {
                1 shl it
            }

            val map = hashMapOf<Int, Int>()

            deliciousness.forEach {
                mealSet.forEach { ms ->
                    map[ms - it]?.also {
                        result += it
                        result %= m
                    }
                }

                map[it] = (map[it] ?: 0) + 1
            }

            return result.toInt()
        }
    }

    expect {
        Solution().countPairs(
            intArrayOf()
        )
    }
}
