package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun decompressRLElist(nums: IntArray): IntArray {
            var index = 0
            val result = arrayListOf<Int>()
            while (index < nums.size) {
                nums[index + 1].also { num ->
                    repeat(nums[index]) {
                        result.add(num)
                    }
                }
                index += 2
            }

            return result.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().decompressRLElist(
            intArrayOf()
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

