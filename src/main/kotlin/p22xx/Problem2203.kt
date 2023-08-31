package p22xx

import util.expect
import util.input
import java.util.*

fun main() {
    class Solution {
        fun minimumWeight(n: Int, edges: Array<IntArray>, src1: Int, src2: Int, dest: Int): Long {
            val negAdjacent = Array(n) { hashMapOf<Int, Long>() }
            val posAdjacent = Array(n) { hashMapOf<Int, Long>() }
            edges.forEach { (from, to, weight) ->
                weight.toLong().also { weightLong ->
                    posAdjacent[from][to] = posAdjacent[from][to]?.takeIf { it < weightLong } ?: weightLong
                    negAdjacent[to][from] = negAdjacent[to][from]?.takeIf { it < weightLong } ?: weightLong
                }
            }

            val max = posAdjacent.sumOf {
                it.values.sum()
            }

            fun findRoute(from: Int, adjacent: Array<out MutableMap<Int, Long>>): LongArray {
                val distance = LongArray(n) { max + 1 }

                val queue = PriorityQueue<Pair<Int, Long>>(compareBy { it.second })
                queue.add(from to 0L)

                while (queue.isNotEmpty()) {
                    val (node, d) = queue.poll()

                    if (d < distance[node]) {
                        distance[node] = d

                        adjacent[node].forEach { (next, weight) ->
                            queue.add(next to d + weight)
                        }
                    }
                }

                return distance
            }

            val destRoutes = findRoute(dest, negAdjacent)
            val src1Routes = findRoute(src1, posAdjacent)
            val src2Routes = findRoute(src2, posAdjacent)

            var result = Long.MAX_VALUE

            destRoutes.forEachIndexed { index, destToCommon ->
                result = result.coerceAtMost(destToCommon + src1Routes[index] + src2Routes[index])
            }

            return result.takeIf { it <= max } ?: -1
        }
    }

    expect(74) {
        Solution().minimumWeight(
            5,
            arrayOf(
                intArrayOf(4, 2, 20),
                intArrayOf(4, 3, 46),
                intArrayOf(0, 1, 15),
                intArrayOf(0, 1, 43),
                intArrayOf(0, 1, 32),
                intArrayOf(3, 1, 13)
            ), 0, 4, 1
        )
    }

    expect {
        Solution().minimumWeight(
            100000, input.first().split("],[").map {
                it.split(",").map { it.toInt() }.toIntArray()
            }.toTypedArray(), 39895, 74993, 71702
        )
    }

    expect(24) {
        Solution().minimumWeight(
            6, arrayOf(
                intArrayOf(0, 2, 10),
                intArrayOf(0, 4, 2),
                intArrayOf(1, 4, 2),
                intArrayOf(1, 3, 10),
                intArrayOf(3, 5, 10),
                intArrayOf(4, 5, 20),
                intArrayOf(2, 5, 10),
            ), 0, 1, 5
        )
    }

    expect(9) {
        Solution().minimumWeight(
            6, arrayOf(
                intArrayOf(0, 2, 2),
                intArrayOf(0, 5, 6),
                intArrayOf(1, 0, 3),
                intArrayOf(1, 4, 5),
                intArrayOf(2, 1, 1),
                intArrayOf(2, 3, 3),
                intArrayOf(2, 3, 4),
                intArrayOf(3, 4, 2),
                intArrayOf(4, 5, 1)
            ), 0, 1, 5
        )
    }
}