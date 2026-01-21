package p35xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minimumPairRemoval(nums: IntArray): Int {
            val sums = LongArray(nums.size) { nums[it].toLong() }
            val startIndices = hashSetOf(0)

            val nexts = IntArray(sums.size) { it + 1 }
            val prevs = IntArray(sums.size) { it - 1 }

            var startIndex = 0
            val sumMap = TreeMap<Long, TreeSet<Int>>()

            fun add(index: Int) {
                sumMap.computeIfAbsent((sums.getOrNull(index) ?: return) + (sums.getOrNull(nexts[index]) ?: return)) { TreeSet() } += index
            }

            fun remove(index: Int) {
                val num1 = sums.getOrNull(index) ?: return
                val num2 = sums.getOrNull(nexts[index]) ?: return

                sumMap[num1 + num2]?.also {
                    if (it.size == 1) {
                        sumMap -= num1 + num2
                    } else {
                        it -= index
                    }
                }
            }

            for (i in 1 until sums.size) {
                if (sums[i] < sums[i - 1]) {
                    startIndex = i
                }

                add(i - 1)

                startIndices += startIndex
            }

            var result = 0


            while (startIndices.size > 1) {
                result++

                val (sum, indices) = sumMap.firstEntry()
                val leftIndex = indices.pollFirst() ?: throw Exception()
                val rightIndex = nexts[leftIndex]

                val leftMoreIndex = prevs[leftIndex]
                val rightMoreIndex = nexts[rightIndex]

                if (indices.isEmpty()) {
                    sumMap -= sum
                }

                remove(leftMoreIndex)
                remove(rightIndex)

                sums[leftIndex] = sum
                nexts[leftIndex] = nexts[rightIndex]
                nexts[leftIndex].takeIf { it < sums.size }?.also {
                    prevs[it] = leftIndex
                }

                add(leftMoreIndex)
                add(leftIndex)

                sums.getOrNull(leftMoreIndex)?.takeIf { it <= sum }?.also {
                    startIndices -= leftIndex
                } ?: run {
                    startIndices += leftIndex
                }
                startIndices -= rightIndex
                sums.getOrNull(rightMoreIndex)?.takeIf { it < sum }?.also {
                    startIndices += rightMoreIndex
                } ?: run { startIndices -= rightMoreIndex }

//                var i = 0
//                buildString {
//                    while (i < sums.size) {
//                        append(sums[i])
//                        append("(${i}) ")
//                        i = nexts[i]
//                    }
//                }.also { println(it) }
//                println(startIndices)
            }
            return result
        }
    }

    expect {
        Solution().minimumPairRemoval(intArrayOf(0, 1, 1, 2, -1, 1))
    }
}
