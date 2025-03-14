package p31xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun scoreOfString(s: String): Int {
            var result = 0
            s.reduce { a, b ->
                result += (a - b).absoluteValue

                b
            }

            return result
        }
    }

    expect {
        Solution().scoreOfString(
            ""
        )
    }
}
