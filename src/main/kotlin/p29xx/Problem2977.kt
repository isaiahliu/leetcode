package p29xx

import util.expect
import java.util.*
import kotlin.math.min

fun main() {
    class Trie {
        var child = arrayOfNulls<Trie>(26)
        var id: Int = -1
    }

    class Solution {
        private val INF = Int.MAX_VALUE / 2

        private fun add(node: Trie, word: String, index: IntArray): Int {
            var node = node
            for (ch in word.toCharArray()) {
                val i = ch.code - 'a'.code
                if (node.child[i] == null) {
                    node.child[i] = Trie()
                }
                node = node.child[i]!!
            }
            if (node.id == -1) {
                node.id = ++index[0]
            }
            return node.id
        }

        private fun update(x: LongArray, y: Long) {
            if (x[0] == -1L || y < x[0]) {
                x[0] = y
            }
        }

        fun minimumCost(source: String, target: String, original: Array<String>, changed: Array<String>, cost: IntArray): Long {
            val n = source.length
            val m = original.size
            val root = Trie()

            val p = intArrayOf(-1)
            val G = Array(m * 2) { IntArray(m * 2) { INF } }

            for (i in 0..<m * 2) {
                G[i][i] = 0
            }

            for (i in 0..<m) {
                val x = add(root, original[i], p)
                val y = add(root, changed[i], p)
                G[x][y] = min(G[x][y], cost[i])
            }

            val size = p[0] + 1
            for (k in 0..<size) {
                for (i in 0..<size) {
                    for (j in 0..<size) {
                        G[i][j] = min(G[i][j], G[i][k] + G[k][j])
                    }
                }
            }

            val f = LongArray(n)
            Arrays.fill(f, -1)
            for (j in 0..<n) {
                if (j > 0 && f[j - 1] == -1L) {
                    continue
                }
                val base = (if (j == 0) 0 else f[j - 1])
                if (source[j] == target[j]) {
                    f[j] = if (f[j] == -1L) base else min(f[j], base)
                }

                var u: Trie = root
                var v: Trie = root
                for (i in j..<n) {
                    u = u.child[source[i].code - 'a'.code] ?: break
                    v = v.child[target.get(i).code - 'a'.code] ?: break
                    if (u.id != -1 && v.id != -1 && G[u.id][v.id] != INF) {
                        val newVal = base + G[u.id][v.id]
                        if (f[i] == -1L || newVal < f[i]) {
                            f[i] = newVal
                        }
                    }
                }
            }

            return f[n - 1]
        }
    }

    expect {
        Solution().minimumCost(
            "abcd", "acbe", arrayOf("a", "b", "c", "c", "e", "d"), arrayOf("b", "c", "b", "e", "b", "e"), intArrayOf(2, 5, 5, 1, 2, 20)
        )
    }
}
