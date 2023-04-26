package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun totalFruit(fruits: IntArray): Int {
            var current = fruits[0]
            var count = 0

            var pre = -1
            var pre2 = -1
            var lastCount = 0
            var sum = 0
            var result = 0
            fun calculate() {
                if (pre2 >= 0 && current != pre2) {
                    sum = lastCount
                }

                lastCount = count
                pre2 = pre
                pre = current
                lastCount = count
                sum += lastCount
                result = result.coerceAtLeast(sum)
            }

            fruits.forEach {
                if (current == it) {
                    count++
                } else {
                    calculate()
                    count = 1
                    current = it
                }
            }

            calculate()

            return result
        }
    }

    measureTimeMillis {
        Solution().totalFruit(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}