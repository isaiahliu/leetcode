package p19xx

import util.expect
import java.math.BigInteger

fun main() {
    class Solution {
        fun numberOfGoodSubsets(nums: IntArray): Int {
            val primeNums = setOf(17, 19, 23, 29)
            val compositeNums = setOf(2, 3, 5, 6, 7, 10, 11, 13, 14, 15, 21, 22, 26, 30)
            val primeCounts = hashMapOf<Int, Int>()
            val compositeCounts = hashMapOf<Int, Int>()
            var oneCounts = 0
            nums.forEach {
                when (it) {
                    1 -> oneCounts++
                    in primeNums -> primeCounts[it] = (primeCounts[it] ?: 0) + 1
                    in compositeNums -> compositeCounts[it] = (compositeCounts[it] ?: 0) + 1
                }
            }

            val countEntries = compositeCounts.entries.toList()

            val m = 1000000007
            val mi = m.toBigInteger()

            var primePossibility = 1L
            primeCounts.values.forEach {
                primePossibility *= (it + 1)
                primePossibility %= m
            }

            fun Int.forEachBit(consumer: (Int) -> Unit) {
                var t = this

                var index = 0
                while (t > 0) {
                    if (t % 2 == 1) {
                        consumer(index)
                    }

                    t /= 2
                    index++
                }
            }

            var compositePossibility = 0L

            repeat(1 shl countEntries.size) { status ->
                var p = 1L
                var prod = BigInteger.ONE

                status.forEachBit {
                    val (num, count) = countEntries[it]

                    if (prod == BigInteger.ZERO || num.toBigInteger().gcd(prod) > BigInteger.ONE) {
                        p = 0L
                    } else {
                        p *= count
                        p %= m
                        prod *= num.toBigInteger()
                    }
                }

                compositePossibility += p
                compositePossibility %= m
            }

            return ((primePossibility.toBigInteger() * compositePossibility.toBigInteger() - BigInteger.ONE) * 2.toBigInteger()
                .modPow(oneCounts.toBigInteger(), mi)).mod(mi).toInt()
        }
    }

    expect(62) {
        Solution().numberOfGoodSubsets(
            intArrayOf(
                6, 8, 1, 8, 6, 5, 6, 11, 17
            )
        )
    }
}