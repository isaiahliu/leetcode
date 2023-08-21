package p04xx

import util.expect

fun main() {
    class Solution {
        fun compress(chars: CharArray): Int {
            var writeIndex = 0

            var lastChar = chars[0] - 1
            var count = 0
            chars.forEach { c ->
                if (c == lastChar) {
                    count++
                } else {
                    if (count > 1) {
                        count.toString().forEach {
                            chars[writeIndex++] = it
                        }
                    }

                    lastChar = c

                    chars[writeIndex++] = c

                    count = 1
                }
            }

            if (count > 1) {
                count.toString().forEach {
                    chars[writeIndex++] = it
                }
            }

            return writeIndex
        }
    }

    expect {
        Solution().compress(
            charArrayOf()
        )
    }
}


