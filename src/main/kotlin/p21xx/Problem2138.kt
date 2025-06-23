package p21xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun divideString(s: String, k: Int, fill: Char): Array<String> {
            return s.padEnd((s.length / k + (s.length % k).sign) * k, fill).chunked(k).toTypedArray()
        }
    }

    expect {
        Solution().divideString(
            "abcdefghij", 1, ' '
        )
    }
}