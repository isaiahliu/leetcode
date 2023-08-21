package p04xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun findRadius(houses: IntArray, heaters: IntArray): Int {
            val heaterMap = TreeSet(heaters.toSet())

            var result = 0

            houses.forEach { house ->
                if (house !in heaters) {
                    var nearBy = Int.MAX_VALUE
                    heaterMap.higher(house)?.also {
                        nearBy = it - house
                    }

                    heaterMap.lower(house)?.also {
                        nearBy = nearBy.coerceAtMost(house - it)
                    }

                    result = result.coerceAtLeast(nearBy)
                }
            }

            return result
        }
    }

    expect {
        Solution().findRadius(
            intArrayOf(), intArrayOf()
        )
    }
}