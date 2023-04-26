package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun subarrayBitwiseORs(arr: IntArray): Int {
            val max = arr.fold(0) { a, b ->
                a or b
            }

            val result = hashSetOf<Int>()
            for (i in arr.indices) {
                var n = arr[i]
                result.add(n)
                for (j in i + 1 until arr.size) {
                    n = n or arr[j]
                    result.add(n)

                    if (n == max) {
                        break
                    }
                }
            }

            return result.size
        }
    }

    measureTimeMillis {
        Solution().subarrayBitwiseORs(
            intArrayOf(1, 1, 2)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}