package p18xx

import util.expect

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

    expect {
        Solution().maxValue(
            "-13", 2
        )
    }
}
