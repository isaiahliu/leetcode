package p29xx

import util.expect

fun main() {
    class Solution {
        fun findChampion(n: Int, edges: Array<IntArray>): Int {
            return ((0 until n) - edges.map { it[1] }.toSet()).takeIf { it.size == 1 }?.first() ?: -1
        }
    }

    expect {
        Solution().findChampion(
            1, arrayOf()
        )
    }
}
