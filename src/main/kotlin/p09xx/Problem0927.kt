package p09xx

import util.expect

fun main() {
    class Solution {
        fun threeEqualParts(arr: IntArray): IntArray {
            val ones = IntArray(arr.size)
            val trailingZeros = IntArray(arr.size)

            var oneIndex = -1

            arr.forEachIndexed { index, digit ->
                when (digit) {
                    1 -> {
                        oneIndex++
                        ones[oneIndex] = index
                    }

                    0 -> {
                        if (oneIndex >= 0) {
                            trailingZeros[oneIndex]++
                        }
                    }

                }
            }

            val oneCount = oneIndex + 1

            if (oneCount == 0) {
                return intArrayOf(0, 2)
            }

            val invalid = intArrayOf(-1, -1)
            if (oneCount % 3 != 0) {
                return invalid
            }

            var index1 = 0
            var index2 = oneCount / 3
            var index3 = oneCount / 3 * 2

            repeat(oneCount / 3 - 1) {
                if (trailingZeros[index1] == trailingZeros[index2] && trailingZeros[index1] == trailingZeros[index3]) {
                    index1++
                    index2++
                    index3++
                } else {
                    return invalid
                }
            }

            if (trailingZeros[index1] < trailingZeros[index3] || trailingZeros[index2] < trailingZeros[index3]) {
                return invalid
            }

            return intArrayOf(ones[index1] + trailingZeros[index3], ones[index2] + trailingZeros[index3] + 1)
        }
    }

    expect {
        Solution().threeEqualParts(
            intArrayOf(1, 1, 0, 0, 1)
        ).toList()
    }
}