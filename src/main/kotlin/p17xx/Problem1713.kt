package p17xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun minOperations(target: IntArray, arr: IntArray): Int {
            val leftDp = IntArray(arr.size) { -1 }
            val rightDp = IntArray(arr.size) { -1 }

            val leftTree = TreeMap<Int, Int>()

            val numMap = target.mapIndexed { index, num -> num to index }.associate { it }.toMap()

            arr.forEachIndexed { index, num ->
                numMap[num]?.also { numIndex ->
                    val weight = (leftTree.lowerEntry(numIndex)?.value ?: 0) + 1
                    leftTree[numIndex] = weight

                    while (true) {
                        leftTree.higherEntry(numIndex)?.takeIf { it.value <= weight }?.also {
                            leftTree.remove(it.key)
                        } ?: break
                    }

                    leftDp[index] = weight
                }
            }

            val rightTree = TreeMap<Int, Int>()
            for (index in arr.lastIndex downTo 0) {
                numMap[arr[index]]?.also { numIndex ->
                    val weight = (rightTree.higherEntry(numIndex)?.value ?: 0) + 1
                    rightTree[numIndex] = weight

                    while (true) {
                        rightTree.lowerEntry(numIndex)?.takeIf { it.value <= weight }?.also {
                            rightTree.remove(it.key)
                        } ?: break
                    }

                    rightDp[index] = weight
                }
            }

            var result = target.size

            leftDp.forEachIndexed { idx, leftCount ->
                result = result.coerceAtMost(target.size - leftCount - rightDp[idx] + 1)
            }

            return result
        }
    }

    expect {
        Solution().minOperations(
            intArrayOf(16, 7, 20, 11, 15, 13, 10, 14, 6, 8), intArrayOf(11, 14, 15, 7, 5, 5, 6, 10, 11, 6)
        )
    }
}
