package p02xx

import util.expect

fun main() {
    class Solution {
        fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
            val dependencies = hashMapOf<Int, MutableSet<Int>>()

            prerequisites.forEach { (c1, c2) ->
                dependencies.computeIfAbsent(c1) { hashSetOf() }.add(c2)
                dependencies.computeIfAbsent(c2) { hashSetOf() }
            }

            while (true) {
                val finishCourse = dependencies.entries.firstOrNull { it.value.isEmpty() } ?: break
                dependencies.remove(finishCourse.key)

                dependencies.forEach {
                    it.value.remove(finishCourse.key)
                }
            }

            return dependencies.isEmpty()
        }
    }

    expect {
        Solution().canFinish(
            1, emptyArray()
        )
    }
}

