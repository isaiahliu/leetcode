package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun shortestCompletingWord(licensePlate: String, words: Array<String>): String {
            val plate = licensePlate.lowercase().filter { it in 'a'..'z' }.toCharArray().also { it.sort() }

            var missing = Int.MAX_VALUE
            var result = ""

            loop@ for (word in words) {
                val w = word.toCharArray().also { it.sort() }

                var l = 0
                var r = 0
                var m = 0

                while (l < w.size && r < plate.size) {
                    when {
                        w[l] < plate[r] -> {
                            m++
                            l++
                        }

                        w[l] > plate[r] -> {
                            continue@loop
                        }

                        else -> {
                            l++
                            r++
                        }
                    }
                }

                if (r < plate.size) {
                    continue@loop
                }

                m += w.size - l

                if (m < missing) {
                    missing = m
                    result = word
                }

                if (m == 0) {
                    break
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().shortestCompletingWord(
            "a",
            arrayOf("as", "a")
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}