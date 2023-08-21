package p08xx

import util.expect

fun main() {
    class Solution {
        fun uncommonFromSentences(s1: String, s2: String): Array<String> {
            val dup = hashSetOf<String>()
            val visited = hashSetOf<String>()

            "$s1 $s2".split(" ").forEach {
                if (it !in dup) {
                    if (!visited.add(it)) {
                        visited.remove(it)
                        dup.add(it)
                    }
                }
            }

            return visited.toTypedArray()
        }
    }

    expect {
        Solution().uncommonFromSentences(
            "this apple is sweet", "this apple is sour"
        )
    }
}