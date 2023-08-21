package p15xx

import util.expect

fun main() {
    class Solution {
        fun canConvertString(s: String, t: String, k: Int): Boolean {
            if (s.length != t.length) {
                return false
            }

            val switch = IntArray(26) {
                it - 26
            }
            s.forEachIndexed { index, c1 ->
                if (t[index] != c1) {
                    val count = ((t[index] - c1 + 26) % 26)

                    switch[count] += 26

                    if (switch[count] > k) {
                        return false
                    }
                }
            }

            return true
        }
    }

    expect {
        Solution().canConvertString(
            "aab", "bbb", 27
        )
    }
}

