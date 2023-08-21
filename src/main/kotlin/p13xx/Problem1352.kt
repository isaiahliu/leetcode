package p13xx

import java.math.BigInteger
import util.expect

fun main() {
    class ProductOfNumbers {
        var last = BigInteger.ONE
        val prod = arrayListOf<BigInteger>()

        fun add(num: Int) {
            if (num == 0) {
                last = BigInteger.ONE
                prod.clear()
            } else {
                last *= num.toBigInteger()
                prod.add(last)
            }
        }

        fun getProduct(k: Int): Int {
            return if (prod.size < k) {
                0
            } else {
                (last / (prod.getOrNull(prod.lastIndex - k) ?: BigInteger.ONE)).toInt()
            }
        }
    }

    expect {
        ProductOfNumbers().add(
            5
        )
    }
}

