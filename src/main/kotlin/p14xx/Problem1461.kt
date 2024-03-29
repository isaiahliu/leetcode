package p14xx

import util.expect

fun main() {
    class Solution {
        fun hasAllCodes(s: String, k: Int): Boolean {
            val mask = (1 shl k) - 1

            var num = 0
            repeat(k.coerceAtMost(s.length)) {
                num *= 2
                num += s[it] - '0'
            }
            val set = hashSetOf(num)

            for (i in k until s.length) {
                num *= 2
                num = num and mask
                num += s[i] - '0'

                set.add(num)

                if (set.size == mask + 1) {
                    return true
                }
            }

            return false
        }
    }

    expect {
        Solution().hasAllCodes(
            "00110", 2
        )

    }
}

