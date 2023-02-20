package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        val cache = hashMapOf<String, List<List<String>>>()
        fun partition(s: String): List<List<String>> {
            if (s.isEmpty()) {
                return listOf(emptyList())
            }

            if (s.length == 1) {
                return listOf(listOf(s))
            }

            if (s in cache) {
                return cache[s].orEmpty()
            }

            val result = arrayListOf<List<String>>()

            for (i in s.indices) {
                var match = true
                for (j in 0..(i - 1) / 2) {
                    if (s[j] != s[i - j]) {
                        match = false
                        break
                    }
                }

                if (match) {
                    val left = listOf(s.substring(0, i + 1))
                    val right = partition(s.substring(i + 1))

                    right.forEach {
                        result.add(left + it)
                    }
                }
            }

            cache[s] = result
            
            return result
        }
    }

    measureTimeMillis {
        Solution().partition(
            "aab"
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

