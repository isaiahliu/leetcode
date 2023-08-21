package p17xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun eatenApples(apples: IntArray, days: IntArray): Int {
            val map = TreeMap<Int, Int>()

            var day = 0

            var result = 0
            while (day < apples.size || map.isNotEmpty()) {
                apples.getOrNull(day)?.also {
                    map[days[day] + day] = (map[days[day] + day] ?: 0) + it
                }

                while (map.isNotEmpty()) {
                    map.firstKey()?.takeIf { it <= day }?.also {
                        map.pollFirstEntry()
                    } ?: break
                }

                map.pollFirstEntry()?.also { (bad, count) ->
                    result++

                    if (count > 1) {
                        map[bad] = count - 1
                    }
                }

                day++
            }

            return result
        }
    }

    expect {
        Solution().eatenApples(
            intArrayOf(1, 2, 3, 5, 2), intArrayOf(3, 2, 1, 4, 2)
        )
    }
}
