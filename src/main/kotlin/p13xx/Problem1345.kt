package p13xx

import util.expect

fun main() {
    class Solution {
        fun minJumps(arr: IntArray): Int {
            val groups = arr.indices.groupBy { arr[it] }.toMutableMap()

            val visited = hashSetOf(0)

            val tasks = hashSetOf(0)

            var result = 0
            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach {
                    if (it == arr.lastIndex) {
                        return result
                    }

                    (groups.remove(arr[it]).orEmpty() + (it - 1) + (it + 1)).forEach {
                        if (it in arr.indices && visited.add(it)) {
                            tasks.add(it)
                        }
                    }
                }

                result++
            }

            return -1
        }
    }

    expect {
        Solution().minJumps(
            IntArray(50000) {
                7
            }.also { it[it.lastIndex] = 11 }
        )
    }
}

