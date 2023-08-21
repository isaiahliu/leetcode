package p14xx

import java.util.*
import util.expect

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

    expect {
        Solution().finalPrices(
            intArrayOf(0, 0, 0, 0, 0)
        )

    }
}

