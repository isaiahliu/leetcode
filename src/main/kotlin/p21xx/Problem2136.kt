package p21xx

import util.expect

fun main() {
    class Solution {
        fun earliestFullBloom(plantTime: IntArray, growTime: IntArray): Int {
            var result = 0

            var plantSum = 0
            plantTime.indices.sortedByDescending { growTime[it] }.forEach {
                plantSum += plantTime[it]

                result = result.coerceAtLeast(plantSum + growTime[it])
            }

            return result
        }
    }

    expect {
        Solution().earliestFullBloom(
            intArrayOf(), intArrayOf()
        )
    }
}