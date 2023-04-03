package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun flipLights(n: Int, presses: Int): Int {
            val tasks = hashSetOf(0)

            val t = "1".repeat(n.coerceAtMost(5)).toInt(2)

            fun addTask(s: Int) {
                tasks.add(s and t)
            }

            repeat(presses.coerceAtMost(8)) {
                tasks.toSet().also { tasks.clear() }.forEach {
                    addTask(it xor 0b11111)
                    addTask(it xor 0b10101)
                    addTask(it xor 0b01010)
                    addTask(it xor 0b01001)
                }
            }

            return tasks.size
        }
    }

    measureTimeMillis {
        Solution().flipLights(
            3, 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}