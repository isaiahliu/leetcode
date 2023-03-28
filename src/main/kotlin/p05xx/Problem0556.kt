package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun nextGreaterElement(n: Int): Int {
            val str = n.toString().toCharArray()

            var last = str[str.lastIndex]

            for (i in str.lastIndex - 1 downTo 0) {
                if (str[i] >= last) {
                    last = str[i]
                } else {
                    val sub = str.drop(i).toMutableList()

                    val midChar = sub.filter { it > str[i] }.min()
                    sub.remove(midChar)

                    return String((str.take(i) + midChar + sub.sorted()).toCharArray()).toIntOrNull() ?: -1
                }
            }
            return -1
        }
    }

    measureTimeMillis {
        Solution().nextGreaterElement(
            12
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}