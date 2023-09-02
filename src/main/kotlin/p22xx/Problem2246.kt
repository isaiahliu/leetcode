package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun longestPath(parent: IntArray, s: String): Int {
            val children = Array(parent.size) {
                hashSetOf<Int>()
            }

            val degrees = IntArray(parent.size)

            val dp = IntArray(parent.size) { 1 }

            parent.forEachIndexed { node, p ->
                if (p >= 0 && s[node] != s[p]) {
                    children[p].add(node)
                    degrees[p]++
                }
            }

            val queue = LinkedList<Int>()
            degrees.forEachIndexed { index, d ->
                if (d == 0) {
                    queue.add(index)
                }
            }

            var result = 1

            while (queue.isNotEmpty()) {
                val node = queue.poll()

                val childrenDps = children[node].sortedByDescending { dp[it] }

                if (childrenDps.isNotEmpty()) {
                    dp[node] += dp[childrenDps[0]]
                    result = result.coerceAtLeast(dp[node] + (childrenDps.getOrNull(1)?.let { dp[it] } ?: 0))
                }

                parent[node].takeIf { it >= 0 }?.also {
                    if (s[it] != s[node]) {
                        degrees[it]--

                        if (degrees[it] == 0) {
                            queue.add(it)
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().longestPath(
            intArrayOf(-1, 0, 0, 1, 1, 2), "abacbe"
        )
    }
}