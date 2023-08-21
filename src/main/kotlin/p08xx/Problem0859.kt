package p08xx

import util.expect

fun main() {
    class Solution {
        fun buddyStrings(s: String, goal: String): Boolean {
            if (s.length != goal.length) {
                return false
            }

            val diffs = arrayListOf<Int>()

            for (index in s.indices) {
                if (s[index] != goal[index]) {
                    when (diffs.size) {
                        0 -> diffs.add(index)
                        1 -> {
                            if (s[index] != goal[diffs[0]] || s[diffs[0]] != goal[index]) {
                                return false
                            }

                            diffs.add(index)
                        }

                        2 -> return false
                    }
                }
            }

            return when (diffs.size) {
                2 -> {
                    true
                }

                0 -> {
                    s.toSet().size != s.length
                }

                else -> {
                    false
                }
            }
        }
    }

    expect {
        Solution().buddyStrings(
            "ab", "ba"
        )

    }
}