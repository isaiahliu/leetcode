package p18xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun getNumberOfBacklogOrders(orders: Array<IntArray>): Int {
            val purchaseOrders = PriorityQueue<Pair<Int, Int>>(compareByDescending { it.first })
            val saleOrders = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

            orders.forEach { (price, amount, type) ->
                var t = amount
                when (type) {
                    0 -> {
                        while (t > 0 && saleOrders.peek()?.first?.takeIf { it <= price } != null) {
                            val (sellPrice, count) = saleOrders.poll()

                            if (count > t) {
                                saleOrders.offer(sellPrice to count - t)

                                t = 0
                            } else {
                                t -= count
                            }
                        }

                        if (t > 0) {
                            purchaseOrders.offer(price to t)
                        }
                    }

                    1 -> {
                        while (t > 0 && purchaseOrders.peek()?.first?.takeIf { it >= price } != null) {
                            val (purchasePrice, count) = purchaseOrders.poll()

                            if (count > t) {
                                purchaseOrders.offer(purchasePrice to count - t)

                                t = 0
                            } else {
                                t -= count
                            }
                        }

                        if (t > 0) {
                            saleOrders.offer(price to t)
                        }
                    }
                }
            }

            return ((purchaseOrders.fold(0L) { a, b ->
                a + b.second
            } + saleOrders.fold(0L) { a, b ->
                a + b.second
            }) % 1000000007).toInt()
        }
    }

    expect {
        Solution().getNumberOfBacklogOrders(
            arrayOf()
        )
    }
}
