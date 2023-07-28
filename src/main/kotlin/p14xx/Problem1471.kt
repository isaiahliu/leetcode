package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getStrongest(arr: IntArray, k: Int): IntArray {
            arr.sort()

            val m = arr[arr.lastIndex / 2]

            return arr.sortedWith(compareByDescending<Int> { if (it > m) it - m else m - it }.thenByDescending { it })
                .take(k).toIntArray()
        }
    }

    measureTimeMillis {
        Solution().getStrongest(
            intArrayOf(2, 5, 1, 3, 4, 7), 3
        ).toList().also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

