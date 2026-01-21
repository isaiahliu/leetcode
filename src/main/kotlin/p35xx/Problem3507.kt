package p35xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minimumPairRemoval(nums: IntArray): Int {
            val startIndices = hashSetOf(0)

            val nexts = IntArray(nums.size) { it + 1 }
            val prevs = IntArray(nums.size) { it - 1 }

            var startIndex = 0
            val sums = TreeMap<Int, TreeSet<Int>>()

            fun add(index: Int) {
                sums.computeIfAbsent((nums.getOrNull(index) ?: return) + (nums.getOrNull(nexts[index]) ?: return)) { TreeSet() } += index
            }

            fun remove(index: Int) {
                val num1 = nums.getOrNull(index) ?: return
                val num2 = nums.getOrNull(nexts[index]) ?: return

                sums[num1 + num2]?.also {
                    if (it.size == 1) {
                        sums -= num1 + num2
                    } else {
                        it -= index
                    }
                }
            }

            for (i in 1 until nums.size) {
                if (nums[i] < nums[i - 1]) {
                    startIndex = i
                }

                add(i - 1)

                startIndices += startIndex
            }

            var result = 0


            while (startIndices.size > 1) {
                result++

                val (sum, indices) = sums.firstEntry()
                val leftIndex = indices.pollFirst() ?: throw Exception()
                val rightIndex = nexts[leftIndex]

                val leftMoreIndex = prevs[leftIndex]
                val rightMoreIndex = nexts[rightIndex]

                if (indices.isEmpty()) {
                    sums -= sum
                }

                remove(leftMoreIndex)
                remove(rightIndex)

                nums[leftIndex] = sum
                nexts[leftIndex] = nexts[rightIndex]
                nexts[leftIndex].takeIf { it < nums.size }?.also {
                    prevs[it] = leftIndex
                }

                add(leftMoreIndex)
                add(leftIndex)

                nums.getOrNull(leftMoreIndex)?.takeIf { it <= sum }?.also {
                    startIndices -= leftIndex
                } ?: run {
                    startIndices += leftIndex
                }
                startIndices -= rightIndex
                nums.getOrNull(rightMoreIndex)?.takeIf { it < sum }?.also {
                    startIndices += rightMoreIndex
                } ?: run { startIndices -= rightMoreIndex }

//                var i = 0
//                buildString {
//                    while (i < nums.size) {
//                        append(nums[i])
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
