package p25xx

import util.expect

fun main() {
    class Solution {
        fun maxOutput(n: Int, edges: Array<IntArray>, price: IntArray): Long {
            val adjacent = Array(n) {
                hashSetOf<Int>()
            }

            edges.forEach { (from, to) ->
                adjacent[from] += to
                adjacent[to] += from
            }

            var result = 0L

            val visited = hashSetOf(0)
            fun dfs(node: Int): Pair<Long, Long> {
                var s1 = 0L
                var s2 = price[node].toLong()

                adjacent[node].forEach {
                    if (visited.add(it)) {
                        val (sub1, sub2) = dfs(it)

                        result = maxOf(result, s1 + sub2, s2 + sub1)

                        s1 = maxOf(s1, sub1 + price[node])
                        s2 = maxOf(s2, sub2 + price[node])
                    }
                }

                return s1 to s2
            }

            val (s1, s2) = dfs(0)

            return maxOf(result, s1, s2 - price[0])
        }
    }

    expect {
        Solution().maxOutput(
            3, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2)), intArrayOf(1, 1, 1)
        )
    }
}

