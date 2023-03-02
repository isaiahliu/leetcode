package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun wiggleSort(nums: IntArray): Unit {
            val sorted = nums.sortedDescending()

            var index = 0

            var writeIndex = 1
            while (writeIndex < nums.size) {
                nums[writeIndex] = sorted[index++]
                writeIndex += 2
            }

            writeIndex = 0
            while (writeIndex < nums.size) {
                nums[writeIndex] = sorted[index++]
                writeIndex += 2
            }
        }
    }

    measureTimeMillis {
        val array = intArrayOf(
            1, 4, 3, 4, 1, 2, 1, 3, 1, 3, 2, 3, 3
        )
        Solution().wiggleSort(
            array
        )

        array.toList().also { println(it) }
    }
}

