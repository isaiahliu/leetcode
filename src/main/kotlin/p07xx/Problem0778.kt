package p07xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun swimInWater(grid: Array<IntArray>): Int {
            val size = grid.size

            if (size == 1) {
                return 0
            }

            val target = size - 1 to size - 1

            val pos = hashMapOf<Int, Pair<Int, Int>>()
            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    pos[num] = r to c
                }
            }

            val visited = hashSetOf(0 to 0, 1 to 0, 0 to 1)
            val map = TreeSet<Int>()
            map.add(grid[0][1])
            map.add(grid[1][0])
            var result = grid[0][0]
            while (true) {
                val near = map.pollFirst() ?: return 0

                result = result.coerceAtLeast(near)

                pos[near]?.also { (r, c) ->
                    arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).forEach {
                        if (it == target) {
                            return result.coerceAtLeast(grid[size - 1][size - 1])
                        } else {
                            grid.getOrNull(it.first)?.getOrNull(it.second)?.also { level ->
                                if (visited.add(it)) {
                                    map.add(level)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    measureTimeMillis {
        Solution().swimInWater(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}