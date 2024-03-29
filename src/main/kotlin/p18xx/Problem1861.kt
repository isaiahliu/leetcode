package p18xx

import util.expect

fun main() {
    class Solution {
        fun rotateTheBox(box: Array<CharArray>): Array<CharArray> {
            val result = Array(box[0].size) { r ->
                CharArray(box.size) { c ->
                    box[box.lastIndex - c][r]
                }
            }

            repeat(result[0].size) { c ->
                var empty = 0
                for (r in result.lastIndex downTo 0) {
                    when (result[r][c]) {
                        '#' -> {
                            if (empty > 0) {
                                result[r + empty][c] = '#'
                                result[r][c] = '.'
                            }
                        }

                        '*' -> {
                            empty = 0
                        }

                        '.' -> {
                            empty++
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().rotateTheBox(
            arrayOf(
                charArrayOf('#', '.', '*', '.'), charArrayOf('#', '#', '*', '.')
            )
        ).joinToString("\n") { String(it) }

    }
}
