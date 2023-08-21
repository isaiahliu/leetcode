package p03xx

import kotlin.math.sqrt
import util.expect

fun main() {
    class Solution {
        fun bulbSwitch(n: Int): Int {
            return sqrt(n.toDouble()).toInt()
        }
    }

    expect {
        Solution().bulbSwitch(
            100
        )
    }
}

