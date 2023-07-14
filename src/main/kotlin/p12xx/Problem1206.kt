package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Skiplist {
        val map = hashMapOf<Int, Int>()

        fun search(target: Int): Boolean {
            return map[target]?.takeIf { it > 0 } != null
        }

        fun add(num: Int) {
            map[num] = (map[num] ?: 0) + 1
        }

        fun erase(num: Int): Boolean {
            return map[num]?.takeIf { it > 0 }?.also {
                map[num] = it - 1
            } != null
        }
    }

    measureTimeMillis {
        Skiplist().search(
            1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
