package p21xx

import util.expect

fun main() {
    class Solution {
        fun countPoints(rings: String): Int {
            val result = IntArray(10)

            for (i in rings.indices step 2) {
                result[rings[i + 1] - '0'] = result[rings[i + 1] - '0'] or (1 shl when (rings[i]) {
                    'R' -> 0
                    'G' -> 1
                    else -> 2
                })
            }

            return result.count { it == 0b111 }
        }
    }

    expect {
        Solution().countPoints(
            ""
        )
    }
}