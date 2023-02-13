package p13xx

fun main() {
    class Solution {
        fun frogPosition(n: Int, edges: Array<IntArray>, t: Int, target: Int): Double {
            val map = hashMapOf<Int, MutableSet<Int>>()

            edges.forEach { (from, to) ->
                map.computeIfAbsent(from) { hashSetOf() }.add(to)
                map.computeIfAbsent(to) { hashSetOf() }.add(from)
            }

            var result = 0.0
            fun walk(second: Int, pos: Int, possibility: Double, route: Set<Int>) {
                val tos = map[pos].orEmpty() - route

                if (second == t || tos.isEmpty()) {
                    if (pos == target) {
                        result += possibility
                    }
                } else {
                    val nextPossibility = possibility / tos.size
                    val nextRoute = route + pos

                    tos.forEach {
                        walk(second + 1, it, nextPossibility, nextRoute)
                    }
                }
            }

            walk(0, 1, 1.0, emptySet())
            return result
        }
    }

    println(
        Solution().frogPosition(
            7, arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(1, 7),
                intArrayOf(2, 4),
                intArrayOf(2, 6),
                intArrayOf(3, 5),
            ), 2, 4
        )
    )
}

