package p19xx

import util.expect

fun main() {
    class Solution {
        fun numberOfWeakCharacters(properties: Array<IntArray>): Int {
            val groups = properties.groupBy({ it[0] }, { it[1] }).toSortedMap(compareByDescending { it })

            var maxDefense = -1

            var result = 0

            groups.forEach { (attack, group) ->
                group.sortedDescending().also {
                    it.forEach {
                        if (it < maxDefense) {
                            result++
                        }
                    }

                    maxDefense = maxDefense.coerceAtLeast(it[0])
                }
            }

            return result
        }
    }

    expect {
        Solution().numberOfWeakCharacters(
            arrayOf(
                intArrayOf(1, 1),
                intArrayOf(2, 1),
                intArrayOf(2, 2),
                intArrayOf(1, 2),
            )
        )
    }
}