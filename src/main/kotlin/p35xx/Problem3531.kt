package p35xx

import util.expect

fun main() {
    class Solution {
        fun countCoveredBuildings(n: Int, buildings: Array<IntArray>): Int {
            val verticals = hashMapOf<Int, IntArray>()
            val horizontals = hashMapOf<Int, IntArray>()

            buildings.forEach { (x, y) ->
                val vertical = verticals.computeIfAbsent(x) { intArrayOf(y, y) }

                vertical[0] = minOf(vertical[0], y)
                vertical[1] = maxOf(vertical[1], y)

                val horizontal = horizontals.computeIfAbsent(y) { intArrayOf(x, x) }
                horizontal[0] = minOf(horizontal[0], x)
                horizontal[1] = maxOf(horizontal[1], x)
            }

            return buildings.count { (x, y) ->
                verticals[x]?.takeIf { (min, max) -> y in (min + 1)..<max } != null &&
                        horizontals[y]?.takeIf { (min, max) -> x in (min + 1)..<max } != null
            }
        }
    }

    expect {
        Solution().countCoveredBuildings(
            1, arrayOf()
        )
    }
}
