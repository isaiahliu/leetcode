package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reorderedPowerOf2(n: Int): Boolean {
            val length = n.toString().length

            var t = 1
            while (t.toString().length < length) {
                t *= 2
            }

            val set = hashSetOf<String>()

            while (t.toString().length == length) {
                set.add(String(t.toString().toCharArray().also { it.sort() }))
                t *= 2
            }

            return String(n.toString().toCharArray().also { it.sort() }) in set
        }
    }

    measureTimeMillis {
        Solution().reorderedPowerOf2(
            1
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}