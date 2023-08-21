package p07xx

import util.expect

fun main() {
    class Solution {
        fun minimumDeleteSum(s1: String, s2: String): Int {
            val cache = hashMapOf<Pair<Int, Int>, Int>()

            fun find(index1: Int, index2: Int): Int {
                val cacheKey = index1 to index2
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }
                val result = when {
                    index1 == s1.length && index2 == s2.length -> {
                        0
                    }

                    index1 == s1.length -> {
                        find(index1, index2 + 1) + s2[index2].code
                    }

                    index2 == s2.length -> {
                        find(index1 + 1, index2) + s1[index1].code
                    }

                    s1[index1] == s2[index2] -> {
                        find(index1 + 1, index2 + 1)
                    }

                    else -> {
                        (find(index1 + 1, index2) + s1[index1].code).coerceAtMost(
                            find(index1, index2 + 1) + s2[index2].code
                        )
                    }
                }

                cache[cacheKey] = result
                return result
            }

            return find(0, 0)
        }
    }

    expect {
        Solution().minimumDeleteSum("sea", "eat")
    }
}