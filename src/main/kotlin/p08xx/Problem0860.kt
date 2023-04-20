package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun lemonadeChange(bills: IntArray): Boolean {
            val counts = IntArray(3)

            fun Int.index(): Int {
                return when (this) {
                    5 -> 0
                    10 -> 1
                    else -> 2
                }
            }
            bills.forEach {
                counts[it.index()]++

                var charge = it - 5
                var chargeIndex = 1
                while (charge > 0) {
                    if (charge == 5 && chargeIndex > 0) {
                        chargeIndex = 0
                    }

                    while (counts[chargeIndex] == 0) {
                        chargeIndex--
                        if (chargeIndex < 0) {
                            return false
                        }
                    }

                    counts[chargeIndex]--
                    charge -= if (chargeIndex == 0) 5 else 10
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().lemonadeChange(
            intArrayOf(5, 5, 5, 20, 20)
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}