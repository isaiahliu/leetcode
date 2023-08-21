package p19xx

import util.expect

fun main() {
    class Solution {
        fun maxProduct(s: String): Long {
            fun String.process(): IntArray {
                val max = IntArray(length + 1)

                val sizes = IntArray(length)
                var center = 0
                var edge = 0

                for (index in 1 until lastIndex) {
                    var size = sizes.getOrNull(center * 2 - index) ?: 0

                    if (index + size >= edge || center * 2 - index - size <= 0) {
                        size = size.coerceAtMost(edge - index)
                        max[index + size + 1] = max[index + size + 1].coerceAtLeast(size)

                        while (true) {
                            val left = getOrNull(index + size + 1) ?: break
                            val right = getOrNull(index - size - 1) ?: break
                            if (left == right) {
                                size++
                                max[index + size + 1] = max[index + size + 1].coerceAtLeast(size)
                            } else {
                                break
                            }
                        }

                        if (index + size > edge) {
                            edge = index + size
                            center = index
                        }
                    }

                    sizes[index] = size
                }

                for (index in 2 until max.lastIndex) {
                    max[index] = max[index].coerceAtLeast(max[index - 1])
                }

                return max
            }

            val leftMax = s.process()
            val rightMax = s.reversed().process()

            var result = 0L
            for (index in 1 until s.length) {
                result =
                    result.coerceAtLeast((leftMax[index] * 2L + 1) * (rightMax[rightMax.lastIndex - index] * 2L + 1))
            }

            return result
        }
    }

    expect {
        Solution().maxProduct(
            "qkryjmcfierisebxlqfamawaihgmcrujrqylenptruncxvliljjfmhpfuswzgenbcykpslldzmansyahgkhsgeocvhdltslphqqubspyqafibmcpitojwpgihjlwbzyeogdzlqzbbzqlzdgoeyzbwljhigpwjotipcmbifaqypsbuqqhplstldhvcoegshkghaysnamzdllspkycbnegzwsufphmfjjlilvxcnurtpnelyqrjurcmghiawamafqlxbesireifcmjyrkq"
        )
    }
}