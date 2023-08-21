package p08xx

import util.expect

fun main() {
    class Solution {
        fun pushDominoes(dominoes: String): String {
            val chars = dominoes.toCharArray()

            var right = hashSetOf<Int>()
            var left = hashSetOf<Int>()

            chars.forEachIndexed { index, c ->
                when (c) {
                    'L' -> {
                        if (!right.remove(index)) {
                            left.add(index)
                        }
                    }

                    'R' -> {
                        if (!left.remove(index)) {
                            right.add(index)
                        }
                    }
                }
            }

            while (left.isNotEmpty() || right.isNotEmpty()) {
                val newRight = hashSetOf<Int>()
                val newLeft = hashSetOf<Int>()

                right.forEach {
                    if (chars.getOrNull(it + 1) == '.') {
                        newRight.add(it + 1)
                    }
                }

                left.forEach {
                    if (chars.getOrNull(it - 1) == '.') {
                        if (!newRight.remove(it - 1)) {
                            newLeft.add(it - 1)
                        }
                    }
                }

                newRight.forEach {
                    chars[it] = 'R'
                }

                newLeft.forEach {
                    chars[it] = 'L'
                }

                right = newRight
                left = newLeft
            }

            return String(chars)
        }
    }

    expect {
        Solution().pushDominoes(
            "RR.L"
        )
    }
}