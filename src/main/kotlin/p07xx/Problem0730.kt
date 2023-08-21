package p07xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun countPalindromicSubsequences(s: String): Int {
            val m = 1000000007

            val indices = Array(4) { TreeSet<Int>() }
            s.forEachIndexed { index, c ->
                indices[c - 'a'].add(index)
            }

            val cache = hashMapOf<Pair<Int, Int>, Long>()
            fun count(startIndex: Int, endIndex: Int): Long {
                when {
                    startIndex > endIndex -> {
                        return 0
                    }

                    startIndex == endIndex -> {
                        return 1
                    }

                    else -> {
                        val cacheKey = startIndex to endIndex
                        if (cacheKey in cache) {
                            return cache[cacheKey] ?: 0
                        }

                        var result = 0L

                        indices.forEach {
                            val charStart = it.higher(startIndex - 1)?.takeIf { it <= endIndex } ?: return@forEach
                            val charEnd = it.lower(endIndex + 1)?.takeIf { it >= startIndex } ?: return@forEach

                            when (charStart) {
                                charEnd -> {
                                    result += 1
                                }

                                else -> {
                                    result += count(charStart + 1, charEnd - 1) + 2
                                }
                            }
                        }

                        result %= m

                        cache[cacheKey] = result

                        return result
                    }
                }
            }

            return count(0, s.lastIndex).toInt()
        }
    }

    expect {
        Solution().countPalindromicSubsequences(
            "abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"
        )
    }
}