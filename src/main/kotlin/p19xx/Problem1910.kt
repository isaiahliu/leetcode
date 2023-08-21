package p19xx

import util.expect

fun main() {
    class Solution {
        fun removeOccurrences(s: String, part: String): String {
            var t = s
            while (true) {
                t.replaceFirst(part, "").takeIf {
                    it.length < t.length
                }?.also {
                    t = it
                } ?: break
            }

            return t
        }
    }

    expect {
        Solution().removeOccurrences(
            "", ""
        )
    }
}
