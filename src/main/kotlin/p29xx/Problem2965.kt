package p29xx

import util.expect

fun main() {
    class Solution {
        fun findMissingAndRepeatedValues(grid: Array<IntArray>): IntArray {
            val result = IntArray(2)

            val set = IntArray(grid.size * grid.size) { it + 1 }.toMutableSet()

            grid.forEach {
                it.forEach {
                    if (!set.remove(it)) {
                        result[0] = it
                    }
                }
            }

            result[1] = set.first()

            return result
        }
    }
    expect {
        Solution().findMissingAndRepeatedValues(
            arrayOf()
        )
    }
}
