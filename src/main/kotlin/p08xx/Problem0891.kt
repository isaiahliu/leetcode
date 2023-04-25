package p08xx

import java.math.BigInteger
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sumSubseqWidths(nums: IntArray): Int {
            val m = 1000000007.toBigInteger()

            nums.sort()

            var result = BigInteger.ZERO
            val two = 2.toBigInteger()

            for (i in 1 until nums.size) {
                val modPow = two.modPow(i.toBigInteger(), m) - BigInteger.ONE
                result -= nums[nums.lastIndex - i].toBigInteger() * modPow
                result += nums[i].toBigInteger() * modPow

                result = result.mod(m)
            }

            return result.toInt()
        }
    }

    measureTimeMillis {
        Solution().sumSubseqWidths(
            intArrayOf(2, 3, 3, 7)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}