package p26xx

import util.expect

fun main() {
    class Solution {
        fun diagonalPrime(nums: Array<IntArray>): Int {
            fun Int.isPrime(): Boolean {
                when {
                    this == 1 -> {
                        return false
                    }

                    this == 2 -> {
                        return true
                    }

                    this % 2 == 0 -> {
                        return false
                    }

                    else -> {
                        for (i in 3 until this) {
                            if (i * i > this) {
                                break
                            }

                            if (this % i == 0) {
                                return false
                            }
                        }

                        return true
                    }
                }
            }

            var result = 0

            nums.forEachIndexed { index, row ->
                intArrayOf(row[index], row[row.lastIndex - index]).filter { it.isPrime() }.forEach {
                    result = maxOf(result, it)
                }
            }

            return result
        }
    }

    expect {
        Solution().diagonalPrime(
            arrayOf()
        )
    }
}
