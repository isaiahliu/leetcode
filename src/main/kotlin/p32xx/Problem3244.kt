package p32xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun shortestDistanceAfterQueries(n: Int, queries: Array<IntArray>): IntArray {
            val roads = TreeMap<Int, Int>()
            roads += 0 to n - 1

            var result = n - 1
            return queries.map { (from, to) ->
                val (lf, lt) = roads.floorEntry(from)

                when {
                    to <= lt -> {
                        roads[lf] = from
                        roads[to] = lt

                        result -= to - from - 1
                    }

                    from <= lt -> {
                        roads[lf] = from
                        result -= lt - from

                        while (true) {
                            val (rf, rt) = roads.higherEntry(lf)

                            if (rf > to) {
                                break
                            }

                            result -= rt - rf + 1
                            roads -= rf

                            if (rt >= to) {
                                roads[to] = rt
                                result += rt - to + 1

                                break
                            }
                        }
                    }
                }
                result
            }.toIntArray()
        }
    }

    expect {
        Solution().shortestDistanceAfterQueries(
            4,
            arrayOf(
                intArrayOf(0, 3),
                intArrayOf(0, 2),
            )
        )
    }
//    expect {
//        Solution().shortestDistanceAfterQueries(
//            100000,
//            Array(100000 - 1) {
//                intArrayOf(0, it + 1)
//            }
//        )
//    }
}
