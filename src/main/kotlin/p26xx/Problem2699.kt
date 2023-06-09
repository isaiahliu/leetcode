package p26xx

import kotlin.system.measureTimeMillis

fun main() {
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

            val possibles = Array(n) { hashSetOf<Int>() }

            val tasks = hashSetOf<Pair<Int, Int>>()
            edges.forEach { (from, to, d) ->
                if (d > 0) {
                    fixedDistances[from][to] = d
                    fixedDistances[to][from] = d

                    tasks.add(from to to)
                } else {
                    possibles[from].add(to)
                    possibles[to].add(from)
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

            fixedDistances[source][destination]?.also {
                when {
                    it == target -> {
                        edges.forEach {
                            if (it[2] == -1) {
                                it[2] = target
                            }
                        }
                    }

                    it < target -> {
                        return emptyArray()
                    }
                }
            }

            return edges
        }
    }

    measureTimeMillis {
        Solution().modifiedGraphEdges(
            4, arrayOf(
                intArrayOf(1, 0, 4),
                intArrayOf(1, 2, 3),
                intArrayOf(2, 3, 5),
                intArrayOf(0, 3, -1)
            ), 0, 2, 6
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}
