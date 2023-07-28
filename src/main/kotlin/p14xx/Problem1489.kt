package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findCriticalAndPseudoCriticalEdges(n: Int, edges: Array<IntArray>): List<List<Int>> {
            class Group {
                var size = 0

                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
                        value.size += size
                    }
                    get() {
                        return innerParent?.parent?.also {
                            innerParent = it
                        } ?: this
                    }

                fun join(target: Group) {
                    val leftParent = parent
                    val rightParent = target.parent

                    if (leftParent != rightParent) {
                        leftParent.parent = rightParent
                    }
                }
            }

            var max = 1
            val sortedEdges = edges.mapIndexed { index, arr ->
                max += arr[2]

                arr to index
            }.sortedBy { it.first[2] }

            fun route(forcedIndex: Int? = null, forbiddenIndex: Int? = null): Int {
                val groups = Array(n) { Group().also { it.size++ } }

                var sumWeight = 0
                val parents = hashSetOf<Group>()

                var size = 1
                parents.add(forcedIndex?.let { edges[it] }?.let { (from, to, weight) ->
                    groups[from].join(groups[to])
                    sumWeight += weight
                    size++
                    groups[from]
                } ?: groups[0])

                for ((edge, edgeIndex) in sortedEdges) {
                    if (edgeIndex == forbiddenIndex) {
                        continue
                    }

                    val (from, to, weight) = edge

                    val fromGroup = groups[from]
                    val toGroup = groups[to]

                    if (fromGroup.parent != toGroup.parent) {
                        fromGroup.join(toGroup)
                        sumWeight += weight
                    }

                    if (parents.none { it.parent == fromGroup.parent }) {
                        parents.add(fromGroup.parent)
                    }

                    size = fromGroup.parent.size

                    if (size == n) {
                        break
                    }
                }

                return sumWeight.takeIf { size == n } ?: max
            }

            val goodEdges = arrayListOf<Int>()
            var minWeight = max
            sortedEdges.forEach { (_, idx) ->
                route(idx).also {
                    if (it < minWeight) {
                        minWeight = it
                        goodEdges.clear()
                    }

                    if (it == minWeight) {
                        goodEdges.add(idx)
                    }
                }
            }

            val r1 = arrayListOf<Int>()
            val r2 = arrayListOf<Int>()
            goodEdges.forEach {
                if (route(forbiddenIndex = it) == minWeight) {
                    r2.add(it)
                } else {
                    r1.add(it)
                }
            }

            return listOf(r1, r2)
        }
    }

    measureTimeMillis {
        Solution().findCriticalAndPseudoCriticalEdges(
            5, arrayOf(
                intArrayOf(0, 1, 1),
                intArrayOf(1, 2, 1),
                intArrayOf(2, 3, 2),
                intArrayOf(0, 3, 2),
                intArrayOf(0, 4, 3),
                intArrayOf(3, 4, 3),
                intArrayOf(1, 4, 6),
            )
        ).toList().also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

