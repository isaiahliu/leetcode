package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sortItems(n: Int, m: Int, group: IntArray, beforeItems: List<List<Int>>): IntArray {
            val visited = hashSetOf<Int>()
            val result = arrayListOf<Int>()

            val groups = Array(m) { hashSetOf<Int>() }

            val remainingGroups = arrayListOf<MutableSet<Int>>()

            repeat(n) {
                val g = group[it]
                if (g >= 0) {
                    groups[g].add(it)
                } else if (beforeItems[it].all { it in visited }) {
                    visited.add(it)
                    result.add(it)
                } else {
                    remainingGroups.add(hashSetOf(it))
                }
            }

            groups.forEach {
                if (it.isNotEmpty()) {
                    remainingGroups.add(it)
                }
            }

            fun Set<Int>.manage(): List<Int>? {
                val tempVisited = hashSetOf<Int>()

                val managed = arrayListOf<Int>()
                val tasks = this.toMutableSet()

                loop@ while (tasks.isNotEmpty()) {
                    for (task in tasks) {
                        if (beforeItems[task].all { it in visited || it in tempVisited }) {
                            managed.add(task)
                            tempVisited.add(task)

                            tasks.remove(task)

                            continue@loop
                        }
                    }

                    return null
                }

                return managed
            }

            loop@ while (remainingGroups.isNotEmpty()) {
                for (g in remainingGroups) {
                    val managedTasks = g.manage()

                    if (managedTasks != null) {
                        visited.addAll(managedTasks)
                        result.addAll(managedTasks)

                        remainingGroups.remove(g)

                        continue@loop
                    }
                }

                return intArrayOf()
            }

            return result.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().sortItems(
            8, 2, intArrayOf(-1, -1, 1, 0, 0, 1, 0, -1), listOf(
                listOf(),
                listOf(6),
                listOf(5),
                listOf(6),
                listOf(3, 6),
                listOf(),
                listOf(),
                listOf(),
            )
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
