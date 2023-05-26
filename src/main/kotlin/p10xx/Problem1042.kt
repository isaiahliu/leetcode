package p10xx

import java.util.*
import kotlin.system.measureTimeMillis


fun main() {
    class Solution {
        fun gardenNoAdj(n: Int, paths: Array<IntArray>): IntArray {
            val pathMap = hashMapOf<Int, MutableSet<Int>>()
            paths.forEach { (from, to) ->
                pathMap.computeIfAbsent(from - 1) { hashSetOf() }.add(to - 1)
                pathMap.computeIfAbsent(to - 1) { hashSetOf() }.add(from - 1)
            }

            val result = IntArray(n)

            val forbiddens = IntArray(n)

            val remainings = result.indices.toMutableSet()

            val nexts = LinkedList<Int>()

            fun Int.flower(): Int {
                return (1..4).first {
                    this and (1 shl it) == 0
                }
            }

            repeat(n) {
                if (nexts.isEmpty()) {
                    val t = remainings.first()
                    remainings.remove(t)
                    nexts.add(t)
                }

                val next = nexts.pop()
                val flower = forbiddens[next].flower()

                result[next] = flower

                pathMap[next]?.forEach {
                    if (remainings.remove(it)) {
                        nexts.add(it)
                    }
                    forbiddens[it] = forbiddens[it] or (1 shl flower)
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().gardenNoAdj(
            3, arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 1),
            )
        ).toList().also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}
