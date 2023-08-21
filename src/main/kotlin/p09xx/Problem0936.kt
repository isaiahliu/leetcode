package p09xx

import util.expect

fun main() {
    class Solution {
        fun movesToStamp(stamp: String, target: String): IntArray {
            val visited = hashSetOf<String>()
            fun String.dfs(depth: Int = 0): List<Int>? {
                if (!visited.add(this)) {
                    return null
                }

                if (depth == 10 * target.length) {
                    return null
                }

                if (this.all { it == '?' }) {
                    return emptyList()
                }

                loop@ for (index in 0 until length - stamp.length + 1) {
                    val chars = toCharArray()
                    var match = false

                    for (stampIndex in stamp.indices) {
                        when (this[index + stampIndex]) {
                            '?' -> {
                            }

                            stamp[stampIndex] -> {
                                chars[index + stampIndex] = '?'
                                match = true
                            }

                            else -> {
                                continue@loop
                            }
                        }
                    }

                    if (match) {
                        String(chars).dfs(depth + 1)?.also {
                            return it + index
                        }
                        break
                    }
                }

                return null
            }

            return target.dfs().orEmpty().toIntArray()
        }
    }

    expect {
        Solution().movesToStamp(
            "abca",
            "aabcaca"
        ).toList()
    }
}