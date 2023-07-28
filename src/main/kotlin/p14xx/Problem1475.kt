package p14xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun finalPrices(prices: IntArray): IntArray {
            val queue = LinkedList<Int>()

            prices.forEachIndexed { index, price ->
                while (queue.isNotEmpty() && prices[queue.peekLast()] >= price) {
                    queue.pollLast().also {
                        prices[it] -= price
                    }
                }

                queue.add(index)
            }

            return prices
        }
    }

    measureTimeMillis {
        Solution().finalPrices(
            intArrayOf(0, 0, 0, 0, 0)
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

