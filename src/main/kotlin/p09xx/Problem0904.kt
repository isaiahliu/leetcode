package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun totalFruit(fruits: IntArray): Int {
            var pre = fruits[0]
            var count = 0

            val list = arrayListOf<Pair<Int, Int>>()
            var sum = 0
            var result = 0
            fun calculate() {
                if (list.size >= 2 && pre != list[list.lastIndex - 1].first) {
                    sum = list[list.lastIndex].second
                }

                list.add(pre to count)
                sum += count
                result = result.coerceAtLeast(sum)
            }

            fruits.forEach {
                if (pre == it) {
                    count++
                } else {
                    calculate()
                    count = 1
                    pre = it
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