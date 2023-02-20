package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        val cache = hashMapOf<String, Int>()

        fun minCut(s: String): Int {
            if (s.length <= 1) {
                return 0
            }

            if (s in cache) {
                return cache[s] ?: 0
            }

            var min = Int.MAX_VALUE

            for (i in s.indices) {
                var match = true
                for (j in 0..(i - 1) / 2) {
                    if (s[j] != s[i - j]) {
                        match = false
                        break
                    }
                }

                if (match) {
                    min = min.coerceAtMost(s.substring(i + 1).takeIf { it.isNotEmpty() }?.let { minCut(it) + 1 } ?: 0)
                }
            }

            cache[s] = min

            return min
        }
    }

    measureTimeMillis {
        Solution().minCut(
            "aa"
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

