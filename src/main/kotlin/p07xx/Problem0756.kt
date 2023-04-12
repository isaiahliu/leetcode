package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun pyramidTransition(bottom: String, allowed: List<String>): Boolean {
            val allowedMap = hashMapOf<Pair<Char, Char>, MutableSet<Char>>()

            allowed.map { it.toCharArray() }.forEach { (b1, b2, t) ->
                allowedMap.computeIfAbsent(b1 to b2) { hashSetOf() }.add(t)
            }

            val pos = hashMapOf<Pair<Int, Int>, Char>()

            bottom.forEachIndexed { index, c ->
                pos[-1 to index] = c
            }

            fun dfs(floor: Int, index: Int): Boolean {
                val base1 = pos[floor - 1 to index] ?: return false
                val base2 = pos[floor - 1 to index + 1] ?: return if (index == 0) {
                    true
                } else {
                    dfs(floor + 1, 0)
                }

                (allowedMap[base1 to base2] ?: return false).forEach { allowedChar ->
                    pos[floor to index] = allowedChar

                    if (dfs(floor, index + 1)) {
                        return true
                    }
                }

                return false
            }

            return dfs(0, 0)
        }
    }

    measureTimeMillis {
        Solution().pyramidTransition(
            "BCD", listOf("BCC", "CDE", "CEA", "FFF")
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}