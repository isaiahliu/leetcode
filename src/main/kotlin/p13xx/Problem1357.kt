package p13xx

import util.expect

fun main() {
    class Cashier(
        private val n: Int,
        private val discount: Int,
        private val products: IntArray,
        private val prices: IntArray
    ) {
        val priceMap = products.indices.associate { products[it] to prices[it] }
        var customerCount = 0

        val discountRate = 1 - discount / 100.0
        fun getBill(product: IntArray, amount: IntArray): Double {
            customerCount++

            val totalAmount = product.mapIndexed { index, p ->
                (priceMap[p] ?: 0) * amount[index]
            }.sum().toDouble()

            return totalAmount * if (customerCount % n == 0) {
                discountRate
            } else {
                1.0
            }
        }
    }

    expect {
        Cashier(5, 1, intArrayOf(), intArrayOf()).getBill(
            intArrayOf(1, 1000000000), intArrayOf()
        )
    }
}

