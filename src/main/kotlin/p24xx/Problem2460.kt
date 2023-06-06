package p24xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun applyOperations(nums: IntArray): IntArray {
            var writeIndex = 0
            var readIndex = 0

            while (readIndex < nums.lastIndex) {
                when (nums[readIndex]) {
                    0 -> {
                        readIndex++
                    }

                    nums[readIndex + 1] -> {
                        nums[writeIndex++] = nums[readIndex++] * 2
                        nums[readIndex++] = 0
                    }

                    else -> {
                        nums[writeIndex++] = nums[readIndex++]
                    }
                }
            }

            nums.getOrNull(readIndex)?.also {
                nums[writeIndex++] = it
            }

            while (writeIndex < nums.size) {
                nums[writeIndex++] = 0
            }

            return nums
        }
    }

    measureTimeMillis {
        Solution().applyOperations(
            intArrayOf(
                312,
                312,
                436,
                892,
                0,
                0,
                528,
                0,
                686,
                516,
                0,
                0,
                0,
                0,
                0,
                445,
                445,
                445,
                445,
                445,
                445,
                984,
                984,
                984,
                0,
                0,
                0,
                0,
                168,
                0,
                0,
                647,
                41,
                203,
                203,
                241,
                241,
                0,
                628,
                628,
                0,
                875,
                875,
                0,
                0,
                0,
                803,
                803,
                54,
                54,
                852,
                0,
                0,
                0,
                958,
                195,
                590,
                300,
                126,
                0,
                0,
                523,
                523
            )
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}