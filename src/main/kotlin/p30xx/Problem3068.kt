package p30xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun maximumValueSum(nums: IntArray, k: Int, edges: Array<IntArray>): Long {
            var result = 0L
            var diffMin = Int.MAX_VALUE
            var adjCount = 0

            nums.forEach {
                val adj = it xor k

                if (adj < it) {
                    result += it
                } else {
                    result += adj
                    adjCount = adjCount xor 1
                }

                diffMin = minOf(diffMin, (adj - it).absoluteValue)
            }

            if (adjCount == 1) {
                result -= diffMin
            }

            return result
        }
    }

    expect {
        Solution().maximumValueSum(
            intArrayOf(1, 2, 1), 3, arrayOf(

            )
        )
    }
}
