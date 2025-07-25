package p34xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maxSubarrays(n: Int, conflictingPairs: Array<IntArray>): Long {
            val ends = TreeMap<Int, Int>()

            conflictingPairs.forEach {
                if (it[0] > it[1]) {
                    val (e, s) = it
                    it[0] = s
                    it[1] = e
                }
                ends[it[1]] = (ends[it[1]] ?: 0) + 1
            }

            conflictingPairs.sortBy { it[0] }

            var index = 0
            var result = 0L

            val save = hashMapOf<Int, Long>()
            for (start in 1..n) {
                val end = ends.firstEntry()?.key ?: (n + 1)
                result += end - start

                if (ends[end] == 1) {
                    save[end] = (save[end] ?: 0) + (ends.higherKey(end) ?: (n + 1)) - end
                }

                while (index < conflictingPairs.size && conflictingPairs[index][0] == start) {
                    conflictingPairs[index++][1].also {
                        ends[it]?.also { c ->
                            if (c == 1) {
                                ends.remove(it)
                            } else {
                                ends[it] = c - 1
                            }
                        }
                    }
                }
            }

            return result + save.values.max()
        }
    }

    expect {
        Solution().maxSubarrays(
            4, arrayOf(
                intArrayOf(2, 3),
                intArrayOf(1, 4),
            )
        )
    }
}
