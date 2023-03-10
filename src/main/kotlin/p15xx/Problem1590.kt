package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minSubarray(nums: IntArray, p: Int): Int {
            val targetMod = nums.fold(0L) { a, b ->
                (a + b) % p
            }

            if (targetMod == 0L) {
                return 0
            }

            val map = hashMapOf(0L to -1)

            var sumM = 0L
            var min = nums.size

            nums.forEachIndexed { index, i ->
                val m = i.toLong() % p

                if (m == targetMod) {
                    return 1
                }

                sumM = (sumM + m) % p

                val requiredM = (sumM - targetMod + p) % p

                map[requiredM]?.also {
                    min = min.coerceAtMost(index - it)
                }

                map[sumM] = index
            }

            return min.takeIf { it < nums.size } ?: -1
        }
    }

    measureTimeMillis {
        Solution().minSubarray(
            intArrayOf(3, 1, 4, 2), 6
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


