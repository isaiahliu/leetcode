package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class MyHashMap {
        val arr = IntArray(1000001) { -1 }

        fun put(key: Int, value: Int) {
            arr[key] = value
        }

        fun get(key: Int): Int {
            return arr[key]
        }

        fun remove(key: Int) {
            put(key, -1)
        }
    }

    measureTimeMillis {
        MyHashMap().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}