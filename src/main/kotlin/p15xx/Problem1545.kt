package p15xx

import util.expect

fun main() {
    class Solution {
        fun findKthBit(n: Int, k: Int): Char {
            var s = 0
            val sizes = IntArray(20) {
                s = s * 2 + 1
                s
            }

            fun find(sizeIndex: Int, pos: Int): Int {
                val size = sizes[sizeIndex]
                return when {
                    sizeIndex == 0 -> {
                        0
                    }

                    pos < size / 2 -> {
                        find(sizeIndex - 1, pos)
                    }

                    pos == size / 2 -> {
                        1
                    }

                    else -> {
                        find(sizeIndex - 1, size - pos - 1) xor 1
                    }
                }
            }

            return '0' + find(n - 1, k - 1)
        }
    }

    expect {
        Solution().findKthBit(
            4, 11
        )
    }
}

