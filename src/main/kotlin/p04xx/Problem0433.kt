package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minMutation(startGene: String, endGene: String, bank: Array<String>): Int {
            fun String.simple(target: String): Boolean {
                return (0 until 8).count { this[it] != target[it] } == 1
            }

            val remaining = bank.toMutableSet()
            if (endGene !in remaining) {
                return -1
            }

            var current = remaining.filter { it.simple(startGene) }.toSet()

            remaining.removeAll(current)

            var result = 1
            while (endGene in remaining) {
                if (current.isEmpty()) {
                    return -1
                }

                current = remaining.filter { r -> current.any { r.simple(it) } }.toSet()
                remaining.removeAll(current)
                result++
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minMutation("AAAAACCC", "AACCCCCC", arrayOf("AAAACCCC", "AAACCCCC", "AACCCCCC")).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


