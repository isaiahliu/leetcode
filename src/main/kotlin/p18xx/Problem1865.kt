package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class FindSumPairs(nums1: IntArray, val nums2: IntArray) {
        val nums1Counts = hashMapOf<Int, Int>()
        val nums2Counts = hashMapOf<Int, Int>()

        init {
            nums1.forEach {
                nums1Counts[it] = (nums1Counts[it] ?: 0) + 1
            }

            nums2.forEach {
                nums2Counts[it] = (nums2Counts[it] ?: 0) + 1
            }
        }

        fun add(index: Int, `val`: Int) {
            val num2 = nums2[index]

            nums2Counts[num2]?.also {
                if (it > 1) {
                    nums2Counts[num2] = it - 1
                } else {
                    nums2Counts.remove(num2)
                }
            }

            nums2Counts[num2 + `val`] = (nums2Counts[num2 + `val`] ?: 0) + 1
            nums2[index] = num2 + `val`
        }

        fun count(tot: Int): Int {
            var result = 0

            nums1Counts.forEach { (num1, count1) ->
                nums2Counts[tot - num1]?.also {
                    result += count1 * it
                }
            }

            return result
        }
    }

    measureTimeMillis {
        FindSumPairs(intArrayOf(), intArrayOf()).count(
            0
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
