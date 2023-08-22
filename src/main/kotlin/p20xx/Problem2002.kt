package p20xx

import util.expect

fun main() {
    class Solution {
        fun maxProduct(s: String): Int {
            val pos = IntArray(s.length) {
                1 shl it
            }

            var cache = emptyArray<IntArray>()
            fun List<Int>.dfs(left: Int = 0, right: Int = lastIndex): Int {
                return when {
                    indices.isEmpty() -> {
                        0
                    }

                    left > right -> {
                        0
                    }

                    left == right -> {
                        1
                    }

                    cache[left][right] >= 0 -> {
                        cache[left][right]
                    }

                    else -> {
                        val result = if (s[this[left]] == s[this[right]]) {
                            2 + dfs(left + 1, right - 1)
                        } else {
                            dfs(left + 1, right).coerceAtLeast(dfs(left, right - 1))
                        }

                        cache[left][right] = result
                        result
                    }
                }
            }

            var result = 0
            repeat(1 shl (s.length - 1)) { status ->
                val left = arrayListOf<Int>()
                val right = arrayListOf<Int>()
                pos.forEachIndexed { index, p ->
                    if (p and status > 0) {
                        left
                    } else {
                        right
                    }.add(index)
                }

                cache = Array(left.size) {
                    IntArray(left.size) { -1 }
                }
                val leftSize = left.dfs()

                cache = Array(right.size) {
                    IntArray(right.size) { -1 }
                }
                val rightSize = right.dfs()
                result = result.coerceAtLeast(
                    leftSize * rightSize
                )
            }

            return result
        }
    }

    expect {
        Solution().maxProduct(
            "nggaagugnk"
        )
    }
}