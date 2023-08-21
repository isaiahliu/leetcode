package p17xx

import util.expect

fun main() {
    class Solution {
        fun findCenter(edges: Array<IntArray>): Int {
            return edges[0].intersect(edges[1].toSet()).first()
        }
    }

    expect {
        Solution().findCenter(
            arrayOf()
        )
    }
}
