package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getKth(lo: Int, hi: Int, k: Int): Int {
            val cache = hashMapOf<Int, Int>()
            fun Int.steps(): Int {
                return if (this == 1) {
                    0
                } else if (this in cache) {
                    cache[this] ?: 0
                } else {
                    var result = 1

                    result += if (this % 2 == 0) {
                        (this / 2).steps()
                    } else {
                        (this * 3 + 1).steps()
                    }

                    cache[this] = result
                    result
                }
            }

            return (lo..hi).sortedWith(compareBy<Int> { it.steps() }.thenBy { it })[k - 1]
        }
    }

    measureTimeMillis {
        Solution().getKth(
            12, 15, 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

