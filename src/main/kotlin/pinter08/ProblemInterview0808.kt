package pinter08

import util.expect

fun main() {
    class Solution {
        fun permutation(S: String): Array<String> {
            val cache = hashMapOf<String, Set<String>>()

            fun dfs(s: String): Set<String> {
                if (s in cache) {
                    return cache[s].orEmpty()
                }

                if (s.length == 1) {
                    return setOf(s)
                }

                val result = hashSetOf<String>()
                for (index in s.indices) {
                    if (s[index] != s.getOrNull(index - 1)) {
                        val lead = s[index]

                        dfs(s.take(index) + s.drop(index + 1)).forEach {
                            result += "$lead$it"
                        }
                    }
                }

                cache[s] = result
                return result
            }

            return dfs(S.toCharArray().also { it.sort() }.concatToString()).toTypedArray()
        }
    }

    expect {
        Solution().permutation("qqe").toList()
    }
}

