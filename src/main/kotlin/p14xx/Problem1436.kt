package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun destCity(paths: List<List<String>>): String {
            val froms = hashSetOf<String>()
            val tos = hashSetOf<String>()

            paths.forEach { (from, to) ->
                froms.add(from)
                tos.add(to)
            }

            tos.removeAll(froms)
            return tos.first()
        }
    }

    measureTimeMillis {
        Solution().destCity(
            listOf()
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

