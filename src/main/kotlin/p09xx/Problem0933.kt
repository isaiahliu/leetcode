package p09xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class RecentCounter {
        val map = TreeMap<Int, Int>()
        fun ping(t: Int): Int {
            map[t] = (map[t] ?: 0) + 1

            return map.subMap(t - 3000, true, t, true).values.sum()
        }
    }

    measureTimeMillis {
        RecentCounter().ping(5).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}