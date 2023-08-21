package p11xx

import util.expect

fun main() {
    class Solution {
        fun alphabetBoardPath(target: String): String {
            val pos = intArrayOf(0, 0)

            val result = StringBuilder()
            target.forEach {
                val r = (it - 'a') / 5
                val c = (it - 'a') % 5

                if (pos[0] > r) {
                    repeat(pos[0] - r) {
                        result.append('U')
                    }
                }

                if (pos[1] > c) {
                    repeat(pos[1] - c) {
                        result.append('L')
                    }
                }

                if (pos[0] < r) {
                    repeat(r - pos[0]) {
                        result.append('D')
                    }
                }

                if (pos[1] < c) {
                    repeat(c - pos[1]) {
                        result.append('R')
                    }
                }

                result.append('!')

                pos[0] = r
                pos[1] = c
            }

            return result.toString()
        }
    }

    expect {
        Solution().alphabetBoardPath(
            "leet"
        )

    }
}