package p09xx

import java.util.*
import util.expect

fun main() {
    class RecentCounter {
        val map = TreeMap<Int, Int>()
        fun ping(t: Int): Int {
            map[t] = (map[t] ?: 0) + 1

            return map.subMap(t - 3000, true, t, true).values.sum()
        }
    }

    expect {
        RecentCounter().ping(5)
    }
}