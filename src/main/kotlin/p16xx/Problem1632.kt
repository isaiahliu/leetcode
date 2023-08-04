package p16xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun matrixRankTransform(matrix: Array<IntArray>): Array<IntArray> {
            class Group {
                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
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

            val groups = Array(matrix.size) {
                Array(matrix[it].size) { Group() }
            }

            val map = TreeMap<Int, MutableSet<Pair<Int, Int>>>()

            val rowGroups = hashMapOf<Pair<Int, Int>, Group>()
            val columnGroups = hashMapOf<Pair<Int, Int>, Group>()

            val result = Array(matrix.size) { r ->
                IntArray(matrix[r].size) { c ->
                    val num = matrix[r][c]
                    map.computeIfAbsent(num) { hashSetOf() }.add(r to c)

                    groups[r][c].join(rowGroups.computeIfAbsent(r to num) { Group() })
                    groups[r][c].join(columnGroups.computeIfAbsent(c to num) { Group() })
                    0
                }
            }

            val rowRanks = IntArray(matrix.size)
            val columnRanks = IntArray(matrix[0].size)

            while (map.isNotEmpty()) {
                val (_, set) = map.pollFirstEntry()

                val groupRanks = hashMapOf<Group, Int>()

                set.forEach { (r, c) ->
                    groups[r][c].parent.also {
                        groupRanks[it] =
                            (groupRanks[it] ?: 0).coerceAtLeast(rowRanks[r] + 1).coerceAtLeast(columnRanks[c] + 1)
                    }
                }

                set.forEach { (r, c) ->
                    val rank = groupRanks[groups[r][c].parent] ?: 0

                    rowRanks[r] = rank
                    columnRanks[c] = rank

                    result[r][c] = rank
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().matrixRankTransform(
            arrayOf(
                intArrayOf(-37, -26, -47, -40, -13),
                intArrayOf(22, -11, -44, 47, -6),
                intArrayOf(-35, 8, -45, 34, -31),
                intArrayOf(-16, 23, -6, -43, -20),
                intArrayOf(47, 38, -27, -8, 43)
            )
        ).map { it.toList() }.also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}