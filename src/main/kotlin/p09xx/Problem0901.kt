package p09xx

import java.util.*
import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        val ss = StockSpanner()
        ss.next(100).also { println(it) }
        ss.next(80).also { println(it) }
        ss.next(60).also { println(it) }
        ss.next(70).also { println(it) }
        ss.next(60).also { println(it) }
        ss.next(75).also { println(it) }
        ss.next(85).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}