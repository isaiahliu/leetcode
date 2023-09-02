package p25xx

import util.expect

fun main() {
    class Solution {
        fun captureForts(forts: IntArray): Int {
            var result = 0

            arrayOf(forts.indices, forts.indices.reversed()).forEach {
                var count = 0
                for (i in it) {
                    when {
                        forts[i] == -1 -> {
                            result = result.coerceAtLeast(count - 1)
                            count = 0
                        }

                        forts[i] == 1 -> {
                            count = 1
                        }

                        count > 0 -> {
                            count++
                        }
                    }
                }
            }
            return result
        }
    }

    expect {
        Solution().captureForts(
            intArrayOf(5, 2, 9, 8, 4)
        )
    }
}