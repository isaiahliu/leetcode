package p03xx

import util.expect

fun main() {
    class Solution {
        fun countSmaller(nums: IntArray): List<Int> {
            val result = IntArray(nums.size)

            val numList = arrayListOf(nums[nums.lastIndex])

            fun binarySearch(leftIndex: Int, rightIndex: Int, target: Int): Int {
                if (leftIndex > rightIndex) {
                    return numList.size
                }

                val midIndex = (leftIndex + rightIndex) / 2

                return if (numList[midIndex] < target) {
                    binarySearch(midIndex + 1, rightIndex, target)
                } else {
                    midIndex.coerceAtMost(binarySearch(leftIndex, midIndex - 1, target))
                }
            }

            for (i in nums.size - 2 downTo 0) {
                val num = nums[i]
                val index = binarySearch(0, numList.lastIndex, num)

                numList.add(index, num)
                result[i] = index
            }

            return result.toList()
        }
    }

    expect {
        Solution().countSmaller(
            intArrayOf(-1, -1)
        )
    }
}

