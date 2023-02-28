package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
            if (n == 1) {
                return listOf(0)
            }

            var minSize = Int.MAX_VALUE

            val map = hashMapOf<Int, MutableSet<Int>>()

            edges.forEach { (from, to) ->
                map.computeIfAbsent(from) { hashSetOf() }.also {
                    it.add(to)
                    minSize = minSize.coerceAtMost(it.size)
                }
                map.computeIfAbsent(to) { hashSetOf() }.also {
                    it.add(from)
                    minSize = minSize.coerceAtMost(it.size)
                }
            }

            while (true) {
                val leaves = map.filterValues { it.size == minSize }
                if (leaves.size == map.size) {
                    break
                }

                for ((key, values) in leaves) {
                    map.remove(key)

                    values.forEach {
                        map[it]?.also {
                            it.remove(key)

                            minSize = minSize.coerceAtMost(it.size)
                        }
                    }
                }
            }

            return map.keys.toList()
        }
    }

    measureTimeMillis {
        Solution().findMinHeightTrees(
            4, arrayOf(
                intArrayOf(1, 0),
                intArrayOf(1, 2),
                intArrayOf(1, 3),
            )
        ).also { println(it) }
    }
}

