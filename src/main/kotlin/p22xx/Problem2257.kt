package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun countUnguarded(m: Int, n: Int, guards: Array<IntArray>, walls: Array<IntArray>): Int {
            val noneEmpties = hashSetOf<Pair<Int, Int>>()
            val GUARD = 1
            val WALL = 0
            val rows = Array(m) { TreeMap<Int, Int>() }
            val columns = Array(n) { TreeMap<Int, Int>() }

            guards.forEach { (r, c) ->
                rows[r][c] = GUARD
                columns[c][r] = GUARD

                noneEmpties.add(r to c)
            }

            walls.forEach { (r, c) ->
                rows[r][c] = WALL
                columns[c][r] = WALL

                noneEmpties.add(r to c)
            }

            var result = 0

            (0 until m).forEach { r ->
                (0 until n).forEach { c ->
                    if (r to c !in noneEmpties) {
                        if (rows[r].lowerEntry(c)?.value != GUARD && rows[r].higherEntry(c)?.value != GUARD
                            && columns[c].lowerEntry(r)?.value != GUARD && columns[c].higherEntry(r)?.value != GUARD
                        ) {
                            result++
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().countUnguarded(
            1, 2, arrayOf(), arrayOf()
        )
    }
}