package p25xx

import util.expect

fun main() {
    class Solution {
        fun sortTheStudents(score: Array<IntArray>, k: Int): Array<IntArray> {
            return score.also { it.sortByDescending { it[k] } }
        }
    }

    expect {
        Solution().sortTheStudents(
            arrayOf(), 1
        )
    }
}
