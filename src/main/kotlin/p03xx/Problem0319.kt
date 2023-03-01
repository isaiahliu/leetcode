package p03xx

import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun bulbSwitch(n: Int): Int {
            return sqrt(n.toDouble()).toInt()
        }
    }

    measureTimeMillis {
        Solution().bulbSwitch(
            100
        ).also { println(it) }
    }
}

