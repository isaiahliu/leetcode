package p05xx

import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    class Solution(val w: IntArray) {
        val sum: Int

        init {
            for (i in 1 until w.size) {
                w[i] += w[i - 1]
            }

            sum = w[w.lastIndex]
        }

        fun binarySearch(startIndex: Int, endIndex: Int, target: Int): Int {
            if (startIndex > endIndex) {
                return w.lastIndex
            }

            val midIndex = (startIndex + endIndex) / 2
            val midNum = w[midIndex]

            return if (target < midNum) {
                midIndex.coerceAtMost(binarySearch(startIndex, midIndex - 1, target))
            } else {
                binarySearch(midIndex + 1, endIndex, target)
            }

        }

        fun pickIndex(): Int {
            return binarySearch(0, w.lastIndex, Random.nextInt(sum))
        }
    }

    measureTimeMillis {
        val sol = Solution(intArrayOf(1, 3))

        sol.pickIndex().also { println(it) }
        sol.pickIndex().also { println(it) }
        sol.pickIndex().also { println(it) }
        sol.pickIndex().also { println(it) }
        sol.pickIndex().also { println(it) }
        sol.pickIndex().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}