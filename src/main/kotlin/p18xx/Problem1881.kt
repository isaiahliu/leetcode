package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxValue(n: String, x: Int): String {
            var comparison = 1
            if (n[0] == '-') {
                comparison = -comparison
            }

            n.forEachIndexed { index, ch ->
                if (x.compareTo(ch - '0') == comparison) {
                    return "${n.take(index)}${x}${n.drop(index)}"
                }
            }

            return n + x
        }
    }

    measureTimeMillis {
        Solution().maxValue(
            "-13", 2
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
