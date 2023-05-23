package p09xx

import java.util.*
import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        TimeMap().set(
            "", "", 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
