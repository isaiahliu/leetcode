package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class MyHashSet {
        val arr = BooleanArray(1000001)

        fun add(key: Int) {
            arr[key] = true
        }

        fun remove(key: Int) {
            arr[key] = false
        }

        fun contains(key: Int): Boolean {
            return arr[key]
        }
    }

    measureTimeMillis {
        MyHashSet().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}