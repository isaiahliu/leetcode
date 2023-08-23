package p20xx

import util.expect
import java.util.*

fun main() {
    class StockPrice {
        val time = TreeMap<Int, Int>()

        val priceMap = TreeMap<Int, Int>()

        fun update(timestamp: Int, price: Int) {
            priceMap[price] = (priceMap[price] ?: 0) + 1

            time[timestamp]?.also { p ->
                priceMap[p]?.also {
                    if (it == 1) {
                        priceMap.remove(p)
                    } else {
                        priceMap[p] = it - 1
                    }
                }
            }

            time[timestamp] = price
        }

        fun current(): Int {
            return time.lastEntry().value
        }

        fun maximum(): Int {
            return priceMap.lastKey()
        }

        fun minimum(): Int {
            return priceMap.firstKey()
        }
    }

    expect {
        StockPrice().current(

        )
    }
}