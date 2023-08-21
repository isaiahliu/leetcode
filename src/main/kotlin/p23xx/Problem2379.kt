package p23xx

import util.expect

fun main() {
    class Solution {
        fun minimumRecolors(blocks: String, k: Int): Int {
            var count = 0
            var max = 0

            var index = 1 - k
            while (index + k - 1 < blocks.length) {
                if (blocks.getOrNull(index - 1) == 'B') {
                    count--
                }

                if (blocks[index + k - 1] == 'B') {
                    count++
                }

                max = max.coerceAtLeast(count)

                index++
            }

            return k - max
        }
    }

    expect {
        Solution().minimumRecolors(
            "WBWBBBW",
            2
        )
    }
}


