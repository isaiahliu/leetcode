package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minPushBox(grid: Array<CharArray>): Int {
            val init = IntArray(4)
            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, n ->
                    when (n) {
                        'S' -> {
                            init[0] = rowIndex
                            init[1] = columnIndex
                        }

                        'B' -> {
                            init[2] = rowIndex
                            init[3] = columnIndex
                        }
                    }
                }
            }

            fun move(person: Pair<Int, Int>, box: Pair<Int, Int>): Set<Pair<Int, Int>> {
                val positionNearby = arrayOf(
                    box.first - 1 to box.second,
                    box.first + 1 to box.second,
                    box.first to box.second - 1,
                    box.first to box.second + 1
                ).filter {
                    grid.getOrNull(it.first)?.getOrNull(it.second)?.takeIf { it != '#' } != null
                }.toMutableSet()

                val result = hashSetOf<Pair<Int, Int>>()
                val visited = hashSetOf(person)
                val tasks = visited.toMutableSet()
                while (positionNearby.isNotEmpty() && tasks.isNotEmpty()) {
                    tasks.toSet().also { tasks.clear() }.forEach {
                        if (positionNearby.remove(it)) {
                            result.add(it)
                        }

                        arrayOf(
                            it.first - 1 to it.second,
                            it.first + 1 to it.second,
                            it.first to it.second - 1,
                            it.first to it.second + 1
                        ).filter {
                            it != box && grid.getOrNull(it.first)?.getOrNull(it.second)?.takeIf { it != '#' } != null
                        }.forEach {
                            if (visited.add(it)) {
                                tasks.add(it)
                            }
                        }
                    }
                }

                return result
            }

            val visited = move(init[0] to init[1], init[2] to init[3]).map { it to (init[2] to init[3]) }.toMutableSet()
            val tasks = visited.toMutableSet()

            var result = 0

            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { (p, b) ->
                    val (br, bc) = b

                    if (grid[br][bc] == 'T') {
                        return result
                    }

                    move(p, b).forEach { (pr, pc) ->
                        val targetPos = (br + br - pr to bc + bc - pc)

                        val g = grid.getOrNull(targetPos.first)?.getOrNull(targetPos.second)

                        if (g != null && g != '#') {
                            val newState = b to targetPos
                            if (visited.add(newState)) {
                                tasks.add(newState)
                            }
                        }
                    }
                }

                result++
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().minPushBox(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}