package p06xx

import kotlin.math.sign
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun repeatedStringMatch(a: String, b: String): Int {
            fun find(start: Int): Int? {
                var index = 0

                while (index < b.length) {
                    if (a[(start + index) % a.length] == b[index]) {
                        index++
                    } else {
                        return null
                    }
                }

                return (start + b.length) / a.length + ((start + b.length) % a.length).sign
            }

            repeat(a.length) {
                return find(it) ?: return@repeat
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().repeatedStringMatch(
            "", ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}