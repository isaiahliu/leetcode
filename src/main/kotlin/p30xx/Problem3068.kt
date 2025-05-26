package p30xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun maximumValueSum(nums: IntArray, k: Int, edges: Array<IntArray>): Long {
            var result = 0L

            var minInc = Int.MAX_VALUE
            var incLeft = false

            nums.forEach {
                val adj = it xor k

                if (adj < it) {
                    result += it
                } else {
                    result += adj
                    incLeft = !incLeft
                }

                minInc = minOf(minInc, (adj - it).absoluteValue)
            }

            if (incLeft) {
                result -= minInc
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
