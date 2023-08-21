package p18xx

import util.expect

fun main() {
    class Solution {
        fun minSwaps(s: String): Int {
            val counts = intArrayOf(0, 0)

            val oneGroups = intArrayOf(0, 0)
            s.forEachIndexed { index, c ->
                counts[c - '0']++

                oneGroups[index % 2] += c - '0'
            }

            return when {
                counts[0] == counts[1] -> {
                    oneGroups[0].coerceAtMost(oneGroups[1])
                }

                counts[0] == counts[1] + 1 -> {
                    oneGroups[0]
                }

                counts[1] == counts[0] + 1 -> {
                    oneGroups[1]
                }

                else -> {
                    -1
                }
            }
        }
    }

    expect {
        Solution().minSwaps(
            "111000"
        )
    }
}
