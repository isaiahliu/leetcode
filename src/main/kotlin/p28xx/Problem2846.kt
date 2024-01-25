package p28xx

import util.expect
import kotlin.math.max

fun main() {
    class Solution {
        fun minOperationsQueries(n: Int, edges: Array<IntArray>, queries: Array<IntArray>): IntArray {
            val m = queries.size
            val neighbors = Array<MutableMap<Int, Int>>(n) {
                hashMapOf()
            }

            for (edge in edges) {
                neighbors[edge[0]][edge[1]] = edge[2]
                neighbors[edge[1]][edge[0]] = edge[2]
            }
            val queryArr = Array<MutableList<IntArray>>(n) {
                arrayListOf()
            }

            for (i in 0 until m) {
                queryArr[queries[i][0]] += intArrayOf(queries[i][1], i)
                queryArr[queries[i][1]] += intArrayOf(queries[i][0], i)
            }

            val count = Array(n) { IntArray(27) }
            val visited = BooleanArray(n)
            val uf = IntArray(n)
            val lca = IntArray(m)
            tarjan(0, -1, neighbors, queryArr, count, visited, uf, lca)
            val res = IntArray(m)
            for (i in 0 until m) {
                var totalCount = 0
                var maxCount = 0
                for (j in 1..26) {
                    val t = count[queries[i][0]][j] + count[queries[i][1]][j] - 2 * count[lca[i]][j]
                    maxCount = max(maxCount.toDouble(), t.toDouble()).toInt()
                    totalCount += t
                }
                res[i] = totalCount - maxCount
            }
            return res
        }

        fun tarjan(
            node: Int,
            parent: Int,
            neighbors: Array<MutableMap<Int, Int>>,
            queryArr: Array<MutableList<IntArray>>,
            count: Array<IntArray>,
            visited: BooleanArray,
            uf: IntArray,
            lca: IntArray
        ) {
            if (parent != -1) {
                System.arraycopy(count[parent], 0, count[node], 0, 27)
                count[node][neighbors[node][parent]!!]++
            }
            uf[node] = node
            for (child in neighbors[node].keys) {
                if (child == parent) {
                    continue
                }
                tarjan(child, node, neighbors, queryArr, count, visited, uf, lca)
                uf[child] = node
            }
            for (pair in queryArr[node]) {
                val node1 = pair[0]
                val index = pair[1]
                if (node != node1 && !visited[node1]) {
                    continue
                }
                lca[index] = find(uf, node1)
            }
            visited[node] = true
        }

        fun find(uf: IntArray, i: Int): Int {
            if (uf[i] == i) {
                return i
            }
            uf[i] = find(uf, uf[i])
            return uf[i]
        }
    }

    expect {
        Solution().minOperationsQueries(
            5, arrayOf(
                intArrayOf(4, 2, 4), intArrayOf(3, 4, 3), intArrayOf(0, 4, 1), intArrayOf(1, 3, 1)
            ), arrayOf(
                intArrayOf(4, 3),
                intArrayOf(3, 1),
                intArrayOf(1, 1),
                intArrayOf(4, 2),
                intArrayOf(1, 4),
                intArrayOf(2, 3),
                intArrayOf(3, 3),
                intArrayOf(4, 1)
            )
        )
    }
}
