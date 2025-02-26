package p29xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumPoints(edges: Array<IntArray>, coins: IntArray, k: Int): Int {
            val adjacent = Array(coins.size) { mutableSetOf<Int>() }
            edges.forEach { (from, to) ->
                adjacent[from] += to
                adjacent[to] += from
            }

            val queue = LinkedList<Int>()
            queue += 0

            val visited = hashSetOf<Int>(0)

            val children = Array(coins.size) { mutableSetOf<Int>() }
            while (queue.isNotEmpty()) {
                val next = queue.poll()

                adjacent[next].forEach {
                    if (visited.add(it)) {
                        children[next] += it
                        queue += it
                    }
                }
            }

            val caches = Array(coins.size) { hashMapOf<Int, Int>() }

            fun dfs(node: Int, cutCount: Int): Int {
                val cache = caches[node]

                cache[cutCount]?.also {
                    return it
                }

                var point = coins[node]

                repeat(cutCount) {
                    point /= 2
                }

                val max = maxOf(point - k + children[node].sumOf {
                    dfs(it, cutCount)
                }, point / 2 + children[node].sumOf {
                    dfs(it, cutCount + 1)
                })

                cache[cutCount] = max

                if (max == 0) {
                    cache[cutCount + 1] = 0
                }

                return max
            }

            return dfs(0, 0)
        }
    }

    expect {
        Solution().maximumPoints(
            arrayOf(), intArrayOf(), 1
        )
    }
}
