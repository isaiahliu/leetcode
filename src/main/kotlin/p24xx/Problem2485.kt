package p24xx

import kotlin.math.sqrt
import util.expect

fun main() {
    class Solution {
        fun pivotInteger(n: Int): Int {
            val sum = n * (n + 1) / 2
            return sqrt(sum.toDouble()).toInt().takeIf { it * it == sum } ?: -1
        }
    }

    expect {
        Solution().pivotInteger(
            8
        )
    }
}