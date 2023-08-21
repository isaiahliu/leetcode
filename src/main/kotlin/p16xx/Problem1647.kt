package p16xx

import util.expect

fun main() {
    class Solution {
        fun minDeletions(s: String): Int {
            val set = hashSetOf<Int>()
            var result = 0
            s.groupingBy { it }.eachCount().values.filter { it > 0 }.sorted().forEach {
                var count = it
                while (count > 0 && !set.add(count)) {
                    count--
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().minDeletions(
            "bbcebab"
        )
    }
}