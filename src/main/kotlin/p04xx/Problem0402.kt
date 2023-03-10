package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun removeKdigits(num: String, k: Int): String {
            val list = num.toMutableList()

            var t = k

            var index = 0
            loop@ while (t > 0) {
                while (index < list.size - 1) {
                    if (index >= 0 && list[index] > list[index + 1]) {
                        t--
                        list.removeAt(index)
                        index--
                        continue@loop
                    }
                    index++
                }
                break@loop
            }

            return list.dropLast(t).joinToString("").trimStart('0').ifEmpty { "0" }
        }
    }

    measureTimeMillis {
        Solution().removeKdigits(
            "10200", 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


