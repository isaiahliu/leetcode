package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkIfExist(arr: IntArray): Boolean {
            val set = hashSetOf<Int>()

            arr.forEach {
                if (it * 2 in set) {
                    return true
                }

                if (it % 2 == 0 && it / 2 in set) {
                    return true
                }

                set.add(it)
            }

            return false
        }
    }

    measureTimeMillis {
        Solution().checkIfExist(
            intArrayOf(10, 2, 5, 3)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

