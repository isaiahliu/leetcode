package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isScramble(s1: String, s2: String): Boolean {
            if (s1 == s2) {
                return true
            }

            val cache = hashMapOf<String, Boolean>()

            fun process(s1Range: IntRange, s2Range: IntRange): Boolean {
                val cacheKey = "${s1Range.first}_${s1Range.last}_${s2Range.first}_${s2Range.last}"

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: false
                }

                if (s1Range.first == s1Range.last) {
                    return s1[s1Range.first] == s2[s2Range.first]
                }

                val s2Heads = hashMapOf<Char, Int>()
                val s2Tails = hashMapOf<Char, Int>()

                for (i in 0 until s1Range.last - s1Range.first) {
                    val s2HeadChar = s2[s2Range.first + i]
                    val s2TailChar = s2[s2Range.last - i]

                    if (s2Heads[s2HeadChar] == -1) {
                        s2Heads.remove(s2HeadChar)
                    } else {
                        s2Heads[s2HeadChar] = (s2Heads[s2HeadChar] ?: 0) + 1
                    }

                    if (s2Tails[s2TailChar] == -1) {
                        s2Tails.remove(s2TailChar)
                    } else {
                        s2Tails[s2TailChar] = (s2Tails[s2TailChar] ?: 0) + 1
                    }

                    val s1Char = s1[s1Range.first + i]

                    if (s2Heads[s1Char] == 1) {
                        s2Heads.remove(s1Char)
                    } else {
                        s2Heads[s1Char] = (s2Heads[s1Char] ?: 0) - 1
                    }

                    if (s2Tails[s1Char] == 1) {
                        s2Tails.remove(s1Char)
                    } else {
                        s2Tails[s1Char] = (s2Tails[s1Char] ?: 0) - 1
                    }

                    if (s2Heads.isEmpty()) {
                        if (process(s1Range.first..s1Range.first + i, s2Range.first..s2Range.first + i) &&
                            process(s1Range.first + i + 1..s1Range.last, s2Range.first + i + 1..s2Range.last)
                        ) {
                            cache[cacheKey] = true
                            return true
                        }
                    }

                    if (s2Tails.isEmpty()) {
                        if (process(s1Range.first..s1Range.first + i, s2Range.last - i..s2Range.last) &&
                            process(s1Range.first + i + 1..s1Range.last, s2Range.first until s2Range.last - i)
                        ) {
                            cache[cacheKey] = true
                            return true
                        }
                    }
                }

                cache[cacheKey] = false
                return false
            }

            return process(s1.indices, s2.indices)
        }
    }

    measureTimeMillis {
        Solution().isScramble(
            "eebaacbcbcadaaedceaaacadccd",
            "eadcaacabaddaceacbceaabeccd"
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

