package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkIfPrerequisite(
            numCourses: Int,
            prerequisites: Array<IntArray>,
            queries: Array<IntArray>
        ): List<Boolean> {
            val parents = Array(numCourses) { hashSetOf<Int>() }

            prerequisites.forEach { (from, to) ->
                parents[to].add(from)
            }

            return queries.map { (from, to) ->
                val visited = parents[to].toMutableSet()
                val tasks = parents[to].toMutableSet()

                while (tasks.isNotEmpty()) {
                    tasks.toSet().also { tasks.clear() }.forEach {
                        if (it == from) {
                            return@map true
                        }

                        parents[it].forEach {
                            if (visited.add(it)) {
                                tasks.add(it)
                            }
                        }
                    }
                }

                false
            }
        }
    }

    measureTimeMillis {
        Solution().checkIfPrerequisite(
            5, arrayOf(), arrayOf()
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

