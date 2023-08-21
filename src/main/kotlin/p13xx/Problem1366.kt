package p13xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun rankTeams(votes: Array<String>): String {
            val ranks = Array(votes[0].length) {
                IntArray(26)
            }

            votes.forEach {
                it.forEachIndexed { index, c ->
                    ranks[index][c - 'A']++
                }
            }

            val set = TreeSet<Char> { c1, c2 ->
                for (rank in ranks) {
                    (rank[c2 - 'A'] - rank[c1 - 'A']).takeIf { it != 0 }?.also {
                        return@TreeSet it
                    }
                }
                c1 - c2
            }

            votes[0].forEach { set.add(it) }

            return set.joinToString("")
        }
    }

    expect {
        Solution().rankTeams(
            arrayOf("ABC", "ACB", "ABC", "ACB", "ACB")
        )
    }
}

