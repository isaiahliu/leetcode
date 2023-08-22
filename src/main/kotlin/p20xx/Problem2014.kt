package p20xx

import util.expect

fun main() {
    class Solution {
        fun longestSubsequenceRepeatedK(s: String, k: Int): String {
            fun String.check(): Boolean {
                var match = 0
                var index = 0

                for (ch in s) {
                    if (ch == this[index]) {
                        index++

                        if (index == length) {
                            index = 0
                            match++

                            if (match == k) {
                                return true
                            }
                        }
                    }
                }

                return false
            }

            val validChars =
                s.groupingBy { it }.eachCount().filter { it.value >= k }.toSortedMap(compareByDescending { it })

            val maxSize = validChars.values.map { it / k }.sum()

            var result: String? = null

            fun dfs(size: Int, route: String) {
                when {
                    result != null -> {
                        return
                    }

                    route.length == size -> {
                        route.takeIf { it.check() }?.also {
                            result = it
                        }

                        return
                    }

                    else -> {
                        val used = route.groupingBy { it }.eachCount().map { it.key to it.value * k }.toMap()

                        validChars.forEach { (ch, count) ->
                            val requireCount = k + (used[ch] ?: 0)

                            if (count >= requireCount) {
                                dfs(size, route + ch)
                            }
                        }
                    }
                }
            }

            for (size in maxSize downTo 1) {
                dfs(size, "")
            }

            return result.orEmpty()
        }
    }

    expect {
        Solution().longestSubsequenceRepeatedK(
            "letsleetcode", 2
        )
    }
}