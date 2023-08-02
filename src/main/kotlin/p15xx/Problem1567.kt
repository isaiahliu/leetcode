package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getMaxLen(nums: IntArray): Int {
            var startIndex = 0
            var negIndex: Int? = null
            var positive = true

            var result = 0
            nums.forEachIndexed { index, i ->
                when {
                    i > 0 -> {
                        result = if (positive) {
                            result.coerceAtLeast(index - startIndex + 1)
                        } else {
                            result.coerceAtLeast(index - (negIndex ?: 0))
                        }
                    }

                    i < 0 -> {
                        if (positive) {
                            negIndex?.also {
                                result = result.coerceAtLeast(index - it)
                            }
                        } else {
                            result = result.coerceAtLeast(index - startIndex + 1)
                        }

                        positive = !positive
                        negIndex = negIndex ?: index
                    }

                    else -> {
                        startIndex = index + 1
                        negIndex = null
                        positive = true
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().getMaxLen(
            intArrayOf(-1)
        ).also { println(it) }
    }
}

