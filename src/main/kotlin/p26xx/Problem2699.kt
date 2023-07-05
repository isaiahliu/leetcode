package p26xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        //答案
        fun modifiedGraphEdges(
            n: Int,
            edges: Array<IntArray>,
            source: Int,
            destination: Int,
            target: Int
        ): Array<IntArray> {
            fun dijkstra(source: Int, destination: Int, adjMatrix: Array<IntArray>): Long {
                // 朴素的 dijkstra 算法
                // adjMatrix 是一个邻接矩阵
                val dist = LongArray(n)
                Arrays.fill(dist, (Int.MAX_VALUE / 2).toLong())
                val used = BooleanArray(n)
                dist[source] = 0
                for (round in 0 until n - 1) {
                    var u = -1
                    for (i in 0 until n) {
                        if (!used[i] && (u == -1 || dist[i] < dist[u])) {
                            u = i
                        }
                    }
                    used[u] = true
                    for (v in 0 until n) {
                        if (!used[v] && adjMatrix[u][v] != -1) {
                            dist[v] = Math.min(dist[v], dist[u] + adjMatrix[u][v])
                        }
                    }
                }
                return dist[destination]
            }

            fun construct(n: Int, edges: Array<IntArray>, idx: Long, target: Int): Array<IntArray> {
                // 需要构造出第 idx 种不同的边权情况，返回一个邻接矩阵
                var index = idx
                val adjMatrix = Array(n) { IntArray(n) }
                for (i in 0 until n) {
                    Arrays.fill(adjMatrix[i], -1)
                }
                for (e in edges) {
                    val u = e[0]
                    val v = e[1]
                    val w = e[2]
                    if (w != -1) {
                        adjMatrix[v][u] = w
                        adjMatrix[u][v] = adjMatrix[v][u]
                    } else {
                        if (index >= target - 1) {
                            adjMatrix[v][u] = target
                            adjMatrix[u][v] = adjMatrix[v][u]
                            index -= (target - 1).toLong()
                        } else {
                            adjMatrix[v][u] = (1 + index).toInt()
                            adjMatrix[u][v] = adjMatrix[v][u]
                            index = 0
                        }
                    }
                }
                return adjMatrix
            }

            var k = 0
            for (e in edges) {
                if (e[2] == -1) {
                    ++k
                }
            }
            if (dijkstra(source, destination, construct(n, edges, 0, target)) > target) {
                return emptyArray()
            }
            if (dijkstra(source, destination, construct(n, edges, k.toLong() * (target - 1), target)) < target) {
                return emptyArray()
            }
            var left: Long = 0
            var right = k.toLong() * (target - 1)
            var ans: Long = 0
            while (left <= right) {
                val mid = (left + right) / 2
                if (dijkstra(source, destination, construct(n, edges, mid, target)) >= target) {
                    ans = mid
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            }
            for (e in edges) {
                if (e[2] == -1) {
                    if (ans >= target - 1) {
                        e[2] = target
                        ans -= (target - 1).toLong()
                    } else {
                        e[2] = (1 + ans).toInt()
                        ans = 0
                    }
                }
            }
            return edges
        }

        //超时
        fun modifiedGraphEdges2(
            n: Int,
            edges: Array<IntArray>,
            source: Int,
            destination: Int,
            target: Int
        ): Array<IntArray> {
            class PeriodLimit(var length: Int)

            val fixedDistances = Array(n) {
                mutableMapOf(it to 0)
            }

            val possibles = Array(n) { hashSetOf<Pair<Int, Int>>() }

            val tasks = PriorityQueue<Pair<Int, Pair<Int, Int>>>(compareBy { it.first })
            edges.forEachIndexed { index, (from, to, d) ->
                if (d < 0) {
                    possibles[from].add(to to index)
                    possibles[to].add(from to index)
                } else if (d <= target) {
                    tasks.add(d to (from to to))
                }
            }

            while (tasks.isNotEmpty()) {
                val (curr, fromto) = tasks.poll()

                val (from, to) = fromto

                if ((from == source && to == destination || from == destination && to == source) && curr <= target) {
                    if (curr < target) {
                        return emptyArray()
                    } else {
                        fixedDistances[from][to] = curr
                        fixedDistances[to][from] = curr
                        break
                    }
                }

                if (fixedDistances[from][to] != null) {
                    continue
                }

                fixedDistances[from][to] = curr
                fixedDistances[to][from] = curr

                val fromReaches = fixedDistances[from]
                val toReaches = fixedDistances[to]

                toReaches.forEach { (reach, d) ->
                    val distance = curr + d
                    if (distance <= target) {
                        tasks.add(distance to (from to reach))
                    }
                }

                fromReaches.forEach { (reach, d) ->
                    val distance = curr + d

                    if (distance <= target) {
                        tasks.add(distance to (to to reach))
                    }
                }
            }

            val distance = fixedDistances[source][destination]
            when {
                distance == null || distance > target -> {
                    val distances = Array(n) { Int.MAX_VALUE to emptyList<Pair<Int, Pair<Int, Int>>>() }
                    distances[source] = 0 to emptyList()

                    val routeTasks =
                        PriorityQueue<Pair<Pair<Int, Int>, List<Pair<Int, Pair<Int, Int>>>>>(compareBy { it.first.second })

                    routeTasks.add(source to 0 to emptyList())
                    while (routeTasks.isNotEmpty()) {
                        val (p, route) = routeTasks.poll()
                        val (current, sumDistance) = p

                        if (current == destination) {
                            continue
                        }

                        possibles[current].forEach { (to, edgeIndex) ->
                            val d = sumDistance + 1

                            if (d <= target && distances[to].first >= d) {
                                distances[to] = d to route + (edgeIndex to (current to to))
                                routeTasks.add(to to d to route + (edgeIndex to (current to to)))
                            }
                        }

                        fixedDistances[current].forEach { (to, dis) ->
                            if (dis > 0) {
                                val d = sumDistance + dis

                                if (d <= target && distances[to].first > d) {
                                    distances[to] = d to route
                                    routeTasks.add(to to d to route)
                                }
                            }
                        }
                    }

                    val targetDistance = distances[destination].first
                    val targetRoute = distances[destination].second.toMutableList()

                    if (targetDistance > target) {
                        return emptyArray()
                    } else {
                        var remaining = target - targetDistance

                        val routeGroups = arrayListOf<MutableList<Pair<Int, Pair<Int, Int>>>>()

                        targetRoute.forEachIndexed { index, r ->
                            if (index == 0 || r.second.first != targetRoute[index - 1].second.second) {
                                routeGroups.add(mutableListOf(r))
                            } else {
                                routeGroups.last().add(r)
                            }
                        }

                        routeGroups.forEach { group ->
                            if (group.size == 1) {
                                group.forEach { (edgeIndex, _) ->
                                    val edge = edges[edgeIndex]
                                    edge[2] = 1
                                    if (remaining > 0) {
                                        edge[2] += remaining.coerceAtMost(
                                            (fixedDistances[edge[0]][edge[1]] ?: Int.MAX_VALUE) - 1
                                        )

                                        remaining -= edge[2] - 1
                                    }
                                }
                            } else {
                                val limits = Array(group.size) { mutableListOf<PeriodLimit>() }

                                for (i in group.indices) {
                                    val left = group[i]
                                    for (j in i until group.size) {
                                        val right = group[j]

                                        fixedDistances[left.second.first][right.second.second]?.let {
                                            PeriodLimit(it - (j - i + 1))
                                        }?.also {
                                            for (k in i..j) {
                                                limits[k].add(it)
                                            }
                                        }
                                    }
                                }

                                group.forEachIndexed { index, (edgeIndex, _) ->
                                    val edge = edges[edgeIndex]
                                    edge[2] = 1
                                    if (remaining > 0) {
                                        val limit = limits[index]

                                        if (limit.isEmpty()) {
                                            edge[2] += remaining.coerceAtMost(
                                                (fixedDistances[edge[0]][edge[1]] ?: Int.MAX_VALUE) - 1
                                            )

                                            remaining -= edge[2] - 1
                                        } else {
                                            val max = limit.minOf { it.length }
                                            edge[2] += remaining.coerceAtMost(max)

                                            limit.forEach { it.length -= max }

                                            remaining -= edge[2] - 1
                                        }
                                    }
                                }
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
        val result = Solution().modifiedGraphEdges(
            4,
            arrayOf(
                intArrayOf(0, 1, 5),
                intArrayOf(1, 2, 7),
                intArrayOf(2, 3, 7),
                intArrayOf(3, 1, 9),
                intArrayOf(3, 0, -1),
                intArrayOf(0, 2, -1)
            ),
            2,
            3,
            7,
        ).also {
            println(it.map { it.toList() })
        }

        Solution().modifiedGraphEdges(20, result, 0, 1, 80).also { println(it.map { it.toList() }) }
    }.also { println("Time cost: ${it}ms") }
}
