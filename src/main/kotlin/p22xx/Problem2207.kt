package p22xx

import util.expect

fun main() {
    class Solution {
        fun maximumSubsequenceCount(text: String, pattern: String): Long {
            var result = 0L

            fun String.count() {
                var count = 0L
                var leftCount = 0L

                forEach {
                    if (it == pattern[1]) {
                        count += leftCount
                    }

                    if (it == pattern[0]) {
                        leftCount++
                    }
                }

                result = result.coerceAtLeast(count)
            }

            "${pattern[0]}$text".count()
            "$text${pattern[1]}".count()

            return result
        }
    }

    expect {
        Solution().maximumSubsequenceCount(
            "acc", "ac"
        )
    }
}