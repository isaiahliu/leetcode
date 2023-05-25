package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canThreePartsEqualSum(arr: IntArray): Boolean {
            val sum = arr.sum()

            if (sum % 3 != 0) {
                return false
            }

            val avg = sum / 3

            var index = 0
            var s = arr[index++]

            while (s != avg) {
                s += arr.getOrNull(index++) ?: return false
            }

            s = arr.getOrNull(index++) ?: return false

            while (s != avg) {
                s += arr.getOrNull(index++) ?: return false
            }

            return index < arr.size
        }
    }

    measureTimeMillis {
        Solution().canThreePartsEqualSum(
            intArrayOf(1, -1, 1, -1)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
