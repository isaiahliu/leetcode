package p21xx

import util.expect

fun main() {
    class Solution {
        fun cellsInRange(s: String): List<String> {
            val (c1, r1, _, c2, r2) = s.toCharArray()

            return (c1..c2).map { c ->
                (r1..r2).map { r ->
                    "$c$r"
                }
            }.flatten()
        }
    }

    expect {
        Solution().cellsInRange(
            "K1:L2"
        )
    }
}