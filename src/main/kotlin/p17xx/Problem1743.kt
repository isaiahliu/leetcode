package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun restoreArray(adjacentPairs: Array<IntArray>): IntArray {
            val map = hashMapOf<Int, MutableSet<Int>>()

            adjacentPairs.forEach { (from, to) ->
                map.computeIfAbsent(from) { hashSetOf() }.add(to)
                map.computeIfAbsent(to) { hashSetOf() }.add(from)
            }

            var left = map.entries.first { it.value.size == 1 }.key

            return IntArray(adjacentPairs.size + 1) {
                left.also {
                    map[left]?.firstOrNull()?.also {
                        map[it]?.remove(left)
                        left = it
                    }
                }
            }
        }
    }

    measureTimeMillis {
        Solution().restoreArray(
            arrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
