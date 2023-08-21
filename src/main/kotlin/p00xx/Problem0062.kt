package p00xx

import java.math.BigInteger
import util.expect

fun main() {
    class Solution {
        fun uniquePaths(m: Int, n: Int): Int {
            val rightCount = (m - 1).toBigInteger()
            val downCount = (n - 1).toBigInteger()

            val min = rightCount.min(downCount).toInt()

            var sum = BigInteger.ONE

            repeat(min) {
                sum *= rightCount + downCount - it.toBigInteger()
            }

            repeat(min) {
                sum /= BigInteger.ONE + it.toBigInteger()
            }

            return sum.toInt()
        }
    }

    expect {
        Solution().uniquePaths(3, 1)
    }
}

