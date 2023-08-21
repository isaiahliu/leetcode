package p04xx

import util.expect

fun main() {
    class Solution {
        fun frequencySort(s: String): String {
            return s.groupingBy { it }.eachCount().entries.sortedByDescending { it.value }
                .joinToString("") { (key, value) ->
                    key.toString().repeat(value)
                }
        }
    }

    expect {
        Solution().frequencySort("")
    }
}