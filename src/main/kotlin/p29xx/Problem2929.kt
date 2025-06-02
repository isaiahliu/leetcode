package p29xx

import util.expect

fun main() {
    class Solution {
        fun distributeCandies(n: Int, limit: Int): Long {
            return c2(n + 2) - 3 * c2(n - limit + 1) + 3 * c2(n - 2 * limit) - c2(n - 3 * limit - 1)
        }

        private fun c2(n: Int): Long {
            return if (n > 1) n.toLong() * (n - 1) / 2 else 0
        }
    }

    expect {
        Solution().distributeCandies(
            3, 4
        )
    }
}
