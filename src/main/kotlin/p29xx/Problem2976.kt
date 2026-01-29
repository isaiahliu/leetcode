package p29xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minimumCost(source: String, target: String, original: CharArray, changed: CharArray, cost: IntArray): Long {
            val froms = hashMapOf<Char, MutableMap<Char, Int>>()
            val tos = hashMapOf<Char, MutableMap<Char, Int>>()

            repeat(26) {
                froms['a' + it] = hashMapOf('a' + it to 0)
                tos['a' + it] = hashMapOf('a' + it to 0)
            }

            val queue = PriorityQueue<Pair<Pair<Char, Char>, Int>>(compareBy { it.second })
            cost.indices.forEach {
                queue.add(original[it] to changed[it] to cost[it])
            }

            while (queue.isNotEmpty()) {
                val (p, c) = queue.poll()
                val (from, to) = p

                if (tos[from]?.get(to) != null) {
                    continue
                }

                tos[to]?.forEach { (nextTo, c2) ->
                    queue.add(from to nextTo to (c + c2))
                }

                froms[from]?.forEach { (prevFrom, c2) ->
                    queue.add(prevFrom to to to (c + c2))
                }

                tos.computeIfAbsent(from) { hashMapOf() }[to] = c
                froms.computeIfAbsent(to) { hashMapOf() }[from] = c
            }

            return source.indices.sumOf {
                tos[source[it]]?.get(target[it])?.toLong() ?: return -1
            }
        }
    }

    expect {
        Solution().minimumCost(
            "abcd", "acbe", charArrayOf('a', 'b', 'c', 'c', 'e', 'd'), charArrayOf('b', 'c', 'b', 'e', 'b', 'e'), intArrayOf(2, 5, 5, 1, 2, 20)
        )
    }
}
