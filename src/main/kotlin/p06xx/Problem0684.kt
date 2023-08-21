package p06xx

import util.expect

fun main() {
    class Solution {
        fun findRedundantConnection(edges: Array<IntArray>): IntArray {
            val pointGroups = arrayListOf<MutableSet<Int>>()

            val result = intArrayOf(0, 0)

            edges.forEach { (from, to) ->
                val fromGroup = pointGroups.firstOrNull { from in it } ?: hashSetOf(from).also { pointGroups.add(it) }
                val toGroup = pointGroups.firstOrNull { to in it } ?: hashSetOf(to).also { pointGroups.add(it) }

                if (fromGroup == toGroup) {
                    result[0] = from
                    result[1] = to
                } else {
                    fromGroup.addAll(toGroup)
                    pointGroups.remove(toGroup)
                }
            }

            return result
        }
    }

    expect {
        Solution().findRedundantConnection(
            arrayOf(
                intArrayOf(9, 10),
                intArrayOf(5, 8),
                intArrayOf(2, 6),
                intArrayOf(1, 5),
                intArrayOf(3, 8),
                intArrayOf(4, 9),
                intArrayOf(8, 10),
                intArrayOf(4, 10),
                intArrayOf(6, 8),
                intArrayOf(7, 9)
            )
        ).toList()
    }
}