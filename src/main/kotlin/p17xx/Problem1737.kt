package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minCharacters(a: String, b: String): Int {
            val aCounts = IntArray(26)
            a.forEach {
                aCounts[it - 'a']++
            }

            val bCounts = IntArray(26)
            b.forEach {
                bCounts[it - 'a']++
            }

            var result = a.length + b.length - aCounts[25] - bCounts[25]

            var aCost = 0
            var bCost = 0
            repeat(25) {
                result = result.coerceAtMost(a.length + b.length - aCounts[it] - bCounts[it])

                aCost += aCounts[it]
                bCost += bCounts[it]
                result = result.coerceAtMost(a.length - aCost + bCost)
                result = result.coerceAtMost(b.length - bCost + aCost)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minCharacters(
            "azzzzz", "bzzzzz"
        ).also { println("${it} should be 2") }

        Solution().minCharacters(
            "a", "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
        ).also { println("${it} should be 2") }

        Solution().minCharacters(
            "bd", "a"
        ).also { println("${it} should be 0") }
    }.also { println("Time cost: ${it}ms") }
}
