package p25xx

import util.expect

fun main() {
    class Solution {
        fun rangeAddQueries(n: Int, queries: Array<IntArray>): Array<IntArray> {
            val diff = Array(n + 2) {
                IntArray(n + 2)
            }
            queries.forEach { (r1, c1, r2, c2) ->
                diff[r1 + 1][c1 + 1]++
                diff[r1 + 1][c2 + 2]--
                diff[r2 + 2][c1 + 1]--
                diff[r2 + 2][c2 + 2]++
            }

            return Array(n) { r ->
                IntArray(n) { c ->
                    diff[r + 1][c + 1] += diff[r + 1][c] + diff[r][c + 1] - diff[r][c]
                    diff[r + 1][c + 1]
                }
            }
        }
    }

    expect {
        Solution().rangeAddQueries(
            1, arrayOf()
        )
    }
}

