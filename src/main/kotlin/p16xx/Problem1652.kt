package p16xx

import util.expect

fun main() {
    class Solution {
        fun decrypt(code: IntArray, k: Int): IntArray {
            val result = IntArray(code.size)

            when {
                k > 0 -> {
                    var sum = code.take(k).sum()

                    repeat(code.size) {
                        sum -= code[it]
                        sum += code[(it + k) % code.size]

                        result[it] = sum
                    }
                }

                k < 0 -> {
                    var sum = code.takeLast(-k).sum()

                    for (index in code.lastIndex downTo 0) {
                        sum -= code[index]
                        sum += code[(index + k + code.size) % code.size]

                        result[index] = sum
                    }
                }
            }
            return result
        }
    }

    expect {
        Solution().decrypt(
            intArrayOf(10, 5, 7, 7, 3, 2, 10, 3, 6, 9, 1, 6), -4
        ).toList()
    }
}