package p21xx

import util.expect

fun main() {
    class Solution {
        fun maximumGood(statements: Array<IntArray>): Int {
            var result = 0
            repeat(1 shl statements.size) { status ->
                var count = 0
                for ((index, s) in statements.withIndex()) {
                    if (status and (1 shl index) > 0) {
                        count++
                        for ((target, judge) in s.withIndex()) {
                            when (judge) {
                                0 -> {
                                    if (status and (1 shl target) > 0) {
                                        return@repeat
                                    }
                                }

                                1 -> {
                                    if (status and (1 shl target) == 0) {
                                        return@repeat
                                    }
                                }
                            }
                        }
                    }
                }

                result = result.coerceAtLeast(count)
            }

            return result
        }
    }

    expect {
        Solution().maximumGood(
            arrayOf()
        )
    }
}