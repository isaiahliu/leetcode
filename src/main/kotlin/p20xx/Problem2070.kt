package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumBeauty(items: Array<IntArray>, queries: IntArray): IntArray {
            val map = TreeMap<Int, Int>()

            items.sortWith(compareBy<IntArray> { it[0] }.thenByDescending { it[1] })
            var max = -1
            items.forEach { (price, beauty) ->
                if (beauty > max) {
                    max = beauty
                    map[price] = beauty
                }
            }

            return queries.map {
                map.floorEntry(it)?.value ?: 0
            }.toIntArray()
        }
    }

    expect {
        Solution().maximumBeauty(arrayOf(), intArrayOf())
    }
}