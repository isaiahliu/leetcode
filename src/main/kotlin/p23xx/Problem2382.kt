package p23xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumSegmentSum(nums: IntArray, removeQueries: IntArray): LongArray {
            var sum = 0L

            val sums = LongArray(nums.size) {
                sum += nums[it]
                sum
            }

            val splits = TreeSet<Int>()
            splits.add(-1)
            splits.add(nums.size)

            val segs = TreeMap<Long, Int>()
            segs[sums.last()] = 1

            return removeQueries.map { split ->
                val lower = splits.lower(split) ?: 0
                val higher = splits.higher(split) ?: 0

                val origSum = sums[higher - 1] - (sums.getOrNull(lower) ?: 0)
                segs[origSum]?.also {
                    if (it == 1) {
                        segs.remove(origSum)
                    } else {
                        segs[origSum] = it - 1
                    }
                }

                arrayOf(sums.getOrElse(split - 1) { 0 } - sums.getOrElse(lower) { 0 },
                    sums[higher - 1] - sums[split]
                ).forEach {
                    segs[it] = (segs[it] ?: 0) + 1
                }

                splits += split
                segs.lastKey()
            }.toLongArray()
        }
    }

    expect {
        Solution().maximumSegmentSum(
            intArrayOf(), intArrayOf()
        )
    }
}