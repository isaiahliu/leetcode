package p09xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
            val map = TreeMap<Int, Int>()

            firstList.forEach { (from, to) ->
                map[from] = to
            }

            val result = arrayListOf<IntArray>()
            secondList.forEach { (from, to) ->
                map.lowerEntry(from)?.also { (f, t) ->
                    if (t >= from) {
                        result.add(intArrayOf(from, t.coerceAtMost(to)))
                    }
                }

                var nextFrom = from - 1
                while (true) {
                    map.higherEntry(nextFrom)?.takeIf { it.key <= to }?.also { (f, t) ->
                        result.add(intArrayOf(f, t.coerceAtMost(to)))
                        nextFrom = t
                    } ?: break
                }
            }

            return result.toTypedArray()
        }
    }

    expect {
        Solution().intervalIntersection(
            arrayOf(), arrayOf()
        )
    }
}
