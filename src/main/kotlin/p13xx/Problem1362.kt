package p13xx

import kotlin.math.sqrt
import util.expect

fun main() {
    class Solution {
        fun closestDivisors(num: Int): IntArray {
            for (left in sqrt((num + 2).toDouble()).toInt() downTo 1) {
                if ((num + 1) % left == 0) {
                    return intArrayOf(left, (num + 1) / left)
                }

                if ((num + 2) % left == 0) {
                    return intArrayOf(left, (num + 2) / left)
                }
            }

            return intArrayOf()
        }
    }

    expect {
        Solution().closestDivisors(
            5
        )
    }
}

