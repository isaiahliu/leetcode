package p26xx

import util.expect

fun main() {
    class Solution {
        fun maximumOr(nums: IntArray, k: Int): Long {
            val digits = LongArray(64)

            operator fun LongArray.minusAssign(str: String) {
                str.forEachIndexed { d, c ->
                    if (c == '1') {
                        digits[d]--
                    }
                }
            }

            operator fun LongArray.plusAssign(str: String) {
                str.forEachIndexed { d, c ->
                    if (c == '1') {
                        digits[d]++
                    }
                }
            }

            fun LongArray.value(): Long {
                var result = 0L
                forEachIndexed { index, count ->
                    if (count > 0) {
                        result += 1L shl index
                    }
                }
                return result
            }

            val strs = nums.map { it.toString(2).reversed() }
            strs.forEach {
                digits += it
            }

            var result = 0L
            strs.forEach {
                val moved = "0".repeat(k) + it

                digits -= it
                digits += moved

                result = maxOf(result, digits.value())

                digits += it
                digits -= moved
            }

            return result
        }
    }

    expect {
        Solution().maximumOr(
            intArrayOf(12, 9), 1
        )
    }
}
