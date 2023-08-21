package p09xx

import java.util.*
import util.expect

fun main() {
    class TimeMap {
        val map = hashMapOf<String, TreeMap<Int, String>>()
        fun set(key: String, value: String, timestamp: Int) {
            map.computeIfAbsent(key) { TreeMap() }[timestamp] = value
        }

        fun get(key: String, timestamp: Int): String {
            return map[key]?.lowerEntry(timestamp + 1)?.value.orEmpty()
        }
    }

    expect {
        TimeMap().set(
            "", "", 1
        )
    }
}
