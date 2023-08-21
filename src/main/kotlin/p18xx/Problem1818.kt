package p18xx

import java.util.*
import kotlin.math.absoluteValue
import util.expect

fun main() {
    class Solution {
        fun minAbsoluteSumDiff(nums1: IntArray, nums2: IntArray): Int {
            val sum = nums1.mapIndexed { index, num1 ->
                (num1 - nums2[index]).absoluteValue
            }.fold(0L) { a, b ->
                a + b
            }

            var maxDiff = 0

            val map = TreeSet<Int>()
            nums1.forEach {
                map.add(it)
            }

            nums2.forEachIndexed { index, num2 ->
                val diff = (nums1[index] - num2).absoluteValue

                map.ceiling(num2)?.also {
                    maxDiff = maxDiff.coerceAtLeast(diff - it + num2)
                }

                map.floor(num2)?.also {
                    maxDiff = maxDiff.coerceAtLeast(diff - num2 + it)
                }
            }

            return ((sum - maxDiff) % 1000000007).toInt()
        }
    }

    expect {
        Solution().minAbsoluteSumDiff(
            intArrayOf(), intArrayOf()
        )
    }
}
