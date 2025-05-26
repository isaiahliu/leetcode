package p30xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun maximumValueSum(nums: IntArray, k: Int, edges: Array<IntArray>): Long {
            var result = 0L

            var minInc = Int.MAX_VALUE
            var incLeft = 0

            nums.forEach {
                val adj = it xor k

                if (adj < it) {
                    result += it
                } else {
                    result += adj
                    incLeft = incLeft xor 1
                }

                minInc = minOf(minInc, (adj - it).absoluteValue)
            }

            result -= minInc * incLeft

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
