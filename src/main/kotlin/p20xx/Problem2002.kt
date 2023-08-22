package p20xx

import util.expect

fun main() {
    class Solution {
        fun maxProduct(s: String): Int {
            val pos = IntArray(s.length) {
                1 shl it
            }

            fun List<Int>.dfs(
                left: Int = 0,
                right: Int = lastIndex,
                cache: Array<IntArray> = Array(size) { IntArray(size) { -1 } }
            ): Int {
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
                            2 + dfs(left + 1, right - 1, cache)
                        } else {
                            dfs(left + 1, right, cache).coerceAtLeast(dfs(left, right - 1, cache))
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

                result = result.coerceAtLeast(
                    left.dfs() * right.dfs()
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