package p22xx

import util.expect
import util.input
import java.util.*

fun main() {
    class Solution {
        fun totalStrength(strength: IntArray): Int {
            fun LongArray.period(lastIndex: Int, length: Int): Long {
                return this[lastIndex] - this.getOrElse(lastIndex - length) { 0 }
            }

            var result = 0L

            val stack = LinkedList<Pair<Int, Int>>()
            val m = 1000000007L

            val sums = LongArray(strength.size) { strength[it].toLong() }

            for (i in 1 until sums.size) {
                sums[i] = (sums[i] + sums[i - 1]).mod(m)
            }

            val sumSums = LongArray(strength.size) { sums[it] }
            for (i in 1 until sumSums.size) {
                sumSums[i] = (sumSums[i - 1] + strength[i].toLong() * (i + 1)).mod(m)
            }

            var prevSums = 0L
            var prevSumSums = 0L
            strength.forEachIndexed { index, numInt ->
                val num = numInt.toLong()

                var size = 1
                while (stack.isNotEmpty() && strength[stack.peek().first] >= num) {
                    val (prevIndex, prevSize) = stack.poll()

                    val summed = (sumSums.period(prevIndex, prevSize) -
                            sums.period(prevIndex, prevSize) * (prevIndex - prevSize + 1) +
                            (sums[index - 1] - sums[prevIndex]) * prevSize).mod(m)

                    prevSums -= prevSize * (strength[prevIndex] - num)

                    prevSumSums = (prevSumSums - summed * (strength[prevIndex] - num)).mod(m)

                    size += prevSize
                }

                prevSums = (prevSums + num).mod(m)
                prevSumSums = (prevSumSums + num * prevSums).mod(m)

                result = (result + prevSumSums).mod(m)

                stack.push(index to size)
            }

            return result.toInt()
        }
    }

    expect(121473332) {
        Solution().totalStrength(
            input.first().split(",").map { it.toInt() }.toIntArray()
        )
    }
    expect(2227) {
        Solution().totalStrength(
            intArrayOf(15, 1, 13, 7, 14, 2, 15)
        )
    }

    expect(8441) {
        Solution().totalStrength(
            intArrayOf(13, 13, 12, 12, 13, 12)
        )
    }

    expect(1478) {
        Solution().totalStrength(
            intArrayOf(14, 9, 14, 3)
        )
    }

    expect(44) {
        Solution().totalStrength(
            intArrayOf(1, 3, 1, 2)
        )
    }

    expect(213) {
        Solution().totalStrength(
            intArrayOf(5, 4, 6)
        )
    }
}