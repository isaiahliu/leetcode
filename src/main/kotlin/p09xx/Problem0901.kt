package p09xx

import java.util.*
import util.expect

fun main() {
    class StockSpanner {
        val list = LinkedList<Pair<Int, Int>>()

        fun next(price: Int): Int {
            var result = 1

            while (list.peek()?.takeIf { it.first <= price } != null) {
                result += list.poll().second
            }

            list.push(price to result)

            return result
        }
    }

    expect {
        val ss = StockSpanner()
        ss.next(100)
        ss.next(80)
        ss.next(60)
        ss.next(70)
        ss.next(60)
        ss.next(75)
        ss.next(85)
    }
}