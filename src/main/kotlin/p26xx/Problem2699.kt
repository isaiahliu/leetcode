package p26xx

import kotlin.system.measureTimeMillis

fun main() {
    //Not done

    class Solution {
        fun modifiedGraphEdges(
            n: Int,
            edges: Array<IntArray>,
            source: Int,
            destination: Int,
            target: Int
        ): Array<IntArray> {
            val fixedDistances = Array(n) {
                mutableMapOf(it to 0)
            }

            val possibles = Array(n) { hashSetOf<Pair<Int, Int>>() }

            val tasks = hashSetOf<Pair<Int, Int>>()
            edges.forEachIndexed { index, (from, to, d) ->
                if (d > 0) {
                    fixedDistances[from][to] = d
                    fixedDistances[to][from] = d

                    tasks.add(from to to)
                } else {
                    possibles[from].add(to to index)
                    possibles[to].add(from to index)
                }
            }

            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { (from, to) ->
                    val curr = fixedDistances[from][to] ?: 0

                    val fromReaches = fixedDistances[from]
                    val toReaches = fixedDistances[to]

                    toReaches.forEach { (reach, d) ->
                        val distance = curr + d
                        if (fromReaches[reach]?.takeIf { it <= distance } == null) {
                            fromReaches[reach] = distance
                            fixedDistances[reach][from] = distance

                            tasks.add(from to reach)
                        }
                    }

                    fromReaches.forEach { (reach, d) ->
                        val distance = curr + d

                        if (toReaches[reach]?.takeIf { it <= distance } == null) {
                            toReaches[reach] = distance
                            fixedDistances[reach][to] = distance

                            tasks.add(to to reach)
                        }
                    }
                }
            }

            val distance = fixedDistances[source][destination]
            when {
                distance == null || distance > target -> {
                    val distances = Array(n) { Int.MAX_VALUE to emptyList<Int>() }
                    distances[source] = 0 to emptyList()

                    fun find(current: Int, sumDistance: Int, route: List<Int>) {
                        if (current == destination) {
                            return
                        }

                        possibles[current].forEach { (to, edgeIndex) ->
                            val d = sumDistance + 1

                            if (distances[to].first >= d) {
                                distances[to] = d to route + edgeIndex
                                find(to, d, route + edgeIndex)
                            }
                        }

                        fixedDistances[current].forEach { (to, dis) ->
                            if (dis > 0) {
                                val d = sumDistance + dis

                                if (distances[to].first > d) {
                                    distances[to] = d to route
                                    find(to, d, route)
                                }
                            }
                        }
                    }

                    find(source, 0, emptyList())

                    val (targetDistance, targetRoute) = distances[destination]
                    if (targetDistance == Int.MAX_VALUE) {
                        return emptyArray()
                    } else if (targetDistance > target) {
                        return emptyArray()
                    } else {
                        var remaining = target - targetDistance

                        targetRoute.reversed().forEach {
                            val edge = edges[it]
                            edge[2] = 1
                            if (remaining > 0) {
                                edge[2] += remaining.coerceAtMost(
                                    (fixedDistances[edge[0]][edge[1]] ?: Int.MAX_VALUE) - 1
                                )

                                remaining -= edge[2] - 1
                            }
                        }

                        if (remaining > 0) {
                            return emptyArray()
                        }
                    }
                }

                distance < target -> {
                    return emptyArray()
                }
            }
            edges.forEach {
                if (it[2] == -1) {
                    it[2] = target
                }
            }

            return edges
        }
    }

    measureTimeMillis {
        Solution().modifiedGraphEdges(
            4,
            arrayOf(
                intArrayOf(3, 0, -1),
                intArrayOf(1, 2, -1),
                intArrayOf(2, 3, -1),
                intArrayOf(1, 3, 9),
                intArrayOf(2, 0, 5)
            ),
            0,
            1,
            7
        ).map { it.toList() }.also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}
