package p08xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun reachableNodes(edges: Array<IntArray>, maxMoves: Int, n: Int): Int {
            val edgeMap = hashMapOf<Int, MutableMap<Int, Int>>()

            edges.forEach { (from, to, nodeCount) ->
                edgeMap.computeIfAbsent(from) { hashMapOf() }[to] = nodeCount
                edgeMap.computeIfAbsent(to) { hashMapOf() }[from] = nodeCount
            }

            val nodePos = IntArray(n) { Int.MAX_VALUE }
            nodePos[0] = 0
            val tasks = PriorityQueue<Int>(compareBy { nodePos[it] })
            tasks.add(0)

            val resultMap = hashMapOf<Pair<Int, Int>, Int>()

            var result = 0
            while (true) {
                val it = tasks.poll() ?: break
                val moveSteps = nodePos[it]
                result++
                edgeMap[it]?.forEach { (target, n) ->
                    val nextStep = moveSteps + n + 1
                    if (nextStep <= maxMoves) {
                        resultMap[it to target] = n

                        if (nodePos[target] > nextStep) {
                            tasks.remove(target)
                            nodePos[target] = nextStep
                            tasks.add(target)
                        }
                    } else {
                        resultMap[it to target] = maxMoves - moveSteps
                    }
                }
            }

            while (resultMap.isNotEmpty()) {
                val (key, nodes) = resultMap.entries.first()
                resultMap.remove(key)
                val (from, to) = key

                val existingNodes = resultMap[to to from] ?: 0

                val maxNode = edgeMap[from]?.get(to) ?: 0

                result += nodes.coerceAtMost(maxNode - existingNodes)
            }
            return result
        }
    }

    expect {
        Solution().reachableNodes(
            arrayOf(
                intArrayOf(0, 3, 8),
                intArrayOf(0, 1, 4),
                intArrayOf(2, 4, 3),
                intArrayOf(1, 2, 0),
                intArrayOf(1, 3, 9),
                intArrayOf(0, 4, 7),
                intArrayOf(3, 4, 9),
                intArrayOf(1, 4, 4),
                intArrayOf(0, 2, 7),
                intArrayOf(2, 3, 1)
            ), 8,
            5
        )
    }
}