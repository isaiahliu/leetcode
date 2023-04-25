package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun fairCandySwap(aliceSizes: IntArray, bobSizes: IntArray): IntArray {
            val bobSet = hashSetOf<Int>()

            var bobSum = 0
            bobSizes.forEach {
                bobSum += it
                bobSet.add(it)
            }

            val aliceSum = aliceSizes.sum()
            val diff = (aliceSum - bobSum) / 2

            aliceSizes.forEach {
                if (it - diff in bobSet) {
                    return intArrayOf(it, it - diff)
                }
            }

            return intArrayOf()
        }
    }

    measureTimeMillis {
        Solution().fairCandySwap(
            intArrayOf(1, 2, 5), intArrayOf(2, 4)
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}