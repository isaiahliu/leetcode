package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun removeKdigits(num: String, k: Int): String {
            val list = num.toMutableList()

            var allInc = false
            repeat(k) {
                if (allInc) {
                    list.removeAt(list.size - 1)
                } else {
                    allInc = true
                    var index = 0
                    while (index < list.size - 1) {
                        if (list[index] > list[index + 1]) {
                            allInc = false
                            break
                        }
                        index++
                    }
                    list.removeAt(index)
                }
            }

            return list.joinToString("").trimStart('0').ifEmpty { "0" }
        }
    }

    measureTimeMillis {
        Solution().removeKdigits(
            "10200", 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


