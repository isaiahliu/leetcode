package p15xx

import util.expect

fun main() {
    class Solution {
        fun unhappyFriends(n: Int, preferences: Array<IntArray>, pairs: Array<IntArray>): Int {
            val matrix = Array(n) { IntArray(n) }

            preferences.forEachIndexed { from, r ->
                r.forEachIndexed { rel, to ->
                    matrix[from][to] = rel
                }
            }

            val result = hashSetOf<Int>()

            fun check(x: Int, y: Int, u: Int, v: Int) {
                if (matrix[x][y] > matrix[x][u] && matrix[u][v] > matrix[u][x]) {
                    result.add(x)
                    result.add(u)
                }
            }
            for (i in pairs.indices) {
                val (x, y) = pairs[i]
                for (j in i + 1 until pairs.size) {
                    val (u, v) = pairs[j]

                    check(x, y, u, v)
                    check(x, y, v, u)
                    check(y, x, u, v)
                    check(y, x, v, u)
                }
            }

            return result.size
        }
    }

    expect {
        Solution().unhappyFriends(
            4, arrayOf(
                intArrayOf(1, 3, 2), intArrayOf(2, 3, 0), intArrayOf(1, 3, 0), intArrayOf(0, 2, 1)
            ), arrayOf(
                intArrayOf(1, 3), intArrayOf(0, 2)
            )
        )
    }
}

