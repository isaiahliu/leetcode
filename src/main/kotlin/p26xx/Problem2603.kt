package p26xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun collectTheCoins(coins: IntArray, edges: Array<IntArray>): Int {
            val adjacent = Array(coins.size) { hashSetOf<Int>() }
            val degrees = IntArray(coins.size)

            edges.forEach { (from, to) ->
                degrees[from]++
                degrees[to]++

                adjacent[from].add(to)
                adjacent[to].add(from)
            }

            val distance = IntArray(coins.size) { Int.MAX_VALUE }

            val queue = LinkedList<Int>()
            degrees.forEachIndexed { index, d ->
                if (d == 1) {
                    queue.add(index)
                    distance[index] = coins[index] - 1
                }
            }

            while (queue.isNotEmpty()) {
                val index = queue.poll()
                var depth = distance[index].coerceAtLeast(coins[index] - 1)

                if (depth >= 0) {
                    depth++
                }

                if (depth <= 1) {
                    adjacent[index].forEach {
                        if (degrees[it] > 1) {
                            if (distance[it] == Int.MAX_VALUE) {
                                distance[it] = depth
                            } else {
                                distance[it] = distance[it].coerceAtLeast(depth)
                            }

                            degrees[it]--

                            if (degrees[it] == 1) {
                                queue.add(it)
                            }
                        }
                    }
                }
            }
            return ((distance.indices.count {
                distance[it] >= 2 || degrees[it] > 1
            } - 1) * 2).coerceAtLeast(0)
        }
    }

    expect {
        Solution().collectTheCoins(
            intArrayOf(1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1), arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(1, 4),
                intArrayOf(4, 5),
                intArrayOf(5, 6),
                intArrayOf(6, 7),
                intArrayOf(3, 8),
                intArrayOf(6, 9),
                intArrayOf(7, 10),
                intArrayOf(10, 11),
                intArrayOf(10, 12),
                intArrayOf(7, 13),
                intArrayOf(12, 14),
                intArrayOf(13, 15),
                intArrayOf(14, 16),
                intArrayOf(15, 17),
                intArrayOf(10, 18)
            )
        )
    }
}