package p05xx

import util.expect

fun main() {
    class Solution {
        fun findPaths(m: Int, n: Int, maxMove: Int, startRow: Int, startColumn: Int): Int {
            val mod = 1000000007

            val map = mutableMapOf(startRow to startColumn to 1L)

            var result = 0L
            repeat(maxMove) {
                map.toMap().also { map.clear() }.forEach { (p, count) ->
                    arrayOf(
                        p.first - 1 to p.second,
                        p.first + 1 to p.second,
                        p.first to p.second - 1,
                        p.first to p.second + 1
                    ).forEach { (r, c) ->
                        if (r == -1 || c == -1 || r == m || c == n) {
                            result += count
                            result %= mod
                        } else {
                            map[r to c] = ((map[r to c] ?: 0L) + count) % mod
                        }
                    }
                }
            }

            return result.toInt()
        }
    }

    expect {
        Solution().findPaths(
            2, 2, 2, 0, 0
        )

    }
}