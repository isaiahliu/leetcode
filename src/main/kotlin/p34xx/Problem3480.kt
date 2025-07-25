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

            var start = 1
            while (index < conflictingPairs.size) {
                val nextStart = conflictingPairs[index][0]

                val end = ends.firstEntry()?.key ?: (n + 1)

                result += (end * 2L - start - nextStart) * (nextStart - start + 1) / 2

                if (ends[end] == 1) {
                    save[end] = (save[end] ?: 0) + 1L * ((ends.higherKey(end) ?: (n + 1)) - end) * (nextStart - start + 1)
                }

                while (index < conflictingPairs.size && conflictingPairs[index][0] == nextStart) {
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

                start = nextStart + 1
            }

            result += (n - start + 2L) * (n - start + 1) / 2

            return result + save.values.max()
        }
    }

    expect {
        Solution().maxSubarrays(
            100000, arrayOf(
                intArrayOf(50000, 50001),
                intArrayOf(99999, 100000),
            )
        )
    }
}
