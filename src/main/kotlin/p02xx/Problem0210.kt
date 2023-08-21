package p02xx

import util.expect

fun main() {
    class Solution {
        fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
            val result = arrayListOf<Int>()

            val dependencies = hashMapOf<Int, MutableSet<Int>>()
            repeat(numCourses) {
                dependencies[it] = hashSetOf()
            }

            prerequisites.forEach { (c1, c2) ->
                dependencies[c1]?.add(c2)
            }

            while (true) {
                val finishCourse = dependencies.entries.firstOrNull { it.value.isEmpty() } ?: break
                dependencies.remove(finishCourse.key)
                result.add(finishCourse.key)

                dependencies.forEach {
                    it.value.remove(finishCourse.key)
                }
            }

            return if (dependencies.isEmpty()) {
                result.toIntArray()
            } else {
                intArrayOf()
            }
        }
    }

    expect {
        Solution().findOrder(
            7, emptyArray()
        )
    }
}

