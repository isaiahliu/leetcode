package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxValue(n: String, x: Int): String {
            if (n[0] == '-') {
                n.forEachIndexed { index, ch ->
                    if (ch - '0' > x) {
                        return "${n.take(index)}${x}${n.drop(index)}"
                    }
                }
            } else {
                n.forEachIndexed { index, ch ->
                    if (ch - '0' < x) {
                        return "${n.take(index)}${x}${n.drop(index)}"
                    }
                }
            }
            return n + x
        }
    }

    measureTimeMillis {
        Solution().maxValue(
            "13", 2
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
