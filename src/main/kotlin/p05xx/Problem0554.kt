package p05xx

import util.expect

fun main() {
    class Solution {
        fun leastBricks(wall: List<List<Int>>): Int {
            val map = hashMapOf<Int, Int>()

            wall.forEach { w ->
                var space = w[0]

                w.drop(1).forEach {
                    map[space] = (map[space] ?: 0) + 1

                    space += it
                }
            }

            return wall.size - (map.values.maxOrNull() ?: 0)
        }
    }

    expect {
        Solution().leastBricks(
            listOf()
        )
    }
}