package p15xx

import util.expect

fun main() {
    class Solution {
        fun countSubTrees(n: Int, edges: Array<IntArray>, labels: String): IntArray {
            val connected = Array(n) { hashSetOf<Int>() }

            edges.forEach { (from, to) ->
                connected[from].add(to)
                connected[to].add(from)
            }

            val result = IntArray(n) { 1 }

            val visited = hashSetOf(0)
            fun dfs(node: Int): Map<Char, Int> {
                val counts = hashMapOf(labels[node] to 1)

                connected[node].forEach {
                    if (visited.add(it)) {
                        dfs(it).forEach { (char, count) ->
                            if (char == labels[node]) {
                                result[node] += count
                            }

                            counts[char] = (counts[char] ?: 0) + count
                        }
                    }
                }

                return counts
            }

            dfs(0)

            return result
        }
    }

    expect {
        Solution().countSubTrees(
            5, arrayOf(), ""
        )
    }
}

