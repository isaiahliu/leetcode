package p23xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumRecolors(blocks: String, k: Int): Int {
            var count = 0
            repeat(k) {
                if (blocks[it] == 'B') {
                    count++
                }
            }

            var max = count

            var index = 1
            while (index + k - 1 < blocks.length) {
                if (blocks[index - 1] == 'B') {
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

    measureTimeMillis {
        Solution().minimumRecolors(
            "WBWBBBW",
            2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


