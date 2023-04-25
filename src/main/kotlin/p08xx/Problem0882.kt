package p08xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reachableNodes(edges: Array<IntArray>, maxMoves: Int, n: Int): Int {
            val edgeMap = hashMapOf<Int, MutableMap<Int, Int>>()

            edges.forEach { (from, to, nodeCount) ->
                edgeMap.computeIfAbsent(from) { hashMapOf() }[to] = nodeCount
                edgeMap.computeIfAbsent(to) { hashMapOf() }[from] = nodeCount
            }

            val nodePos = hashMapOf(0 to 0)
            val tasks = TreeMap<Int, MutableSet<Int>>()
            tasks[0] = hashSetOf(0)

            val resultMap = hashMapOf<Pair<Int, Int>, Int>()

            var result = 0
            while (tasks.isNotEmpty()) {
                val (moveSteps, currents) = tasks.pollFirstEntry()

                currents.forEach {
                    result++
                    edgeMap[it]?.forEach { (target, n) ->
                        val nextStep = moveSteps + n + 1
                        if (nextStep <= maxMoves) {
                            if (nodePos[target]?.takeIf { it < moveSteps } == null) {
                                resultMap[it to target] = n

                                nodePos[target]?.also { existingStep ->
                                    if (existingStep > nextStep) {
                                        tasks[existingStep]?.also {
                                            it.remove(target)
                                            if (it.isEmpty()) {
                                                tasks.remove(existingStep)
                                            }
                                        }

                                        nodePos[target] = nextStep
                                        tasks.computeIfAbsent(nextStep) { hashSetOf() }.add(target)
                                    }
                                } ?: run {
                                    nodePos[target] = nextStep
                                    tasks.computeIfAbsent(nextStep) { hashSetOf() }.add(target)
                                }
                            }
                        } else {
                            resultMap[it to target] = maxMoves - moveSteps
                        }
                    }
                }
            }

            println(resultMap)
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

    measureTimeMillis {
        Solution().reachableNodes(
            arrayOf(
                intArrayOf(0, 1, 4),
                intArrayOf(1, 2, 6),
                intArrayOf(0, 2, 8),
                intArrayOf(1, 3, 1),
            ), 10, 4
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}