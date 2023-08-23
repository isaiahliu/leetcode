package p20xx

import util.expect
import java.util.*
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun minimumDifference(nums: IntArray): Int {
            fun Int.forEachBit(consumer: (Int) -> Unit) {
                var t = this

                var index = 0
                while (t > 0) {
                    if (t % 2 == 1) {
                        consumer(index)
                    }

                    t /= 2
                    index++
                }
            }

            val halfLength = nums.size / 2

            fun generateSums(offset: Int): Array<TreeSet<Int>> {
                val halfSum = nums.drop(offset).take(halfLength).sum()
                val sums = Array(halfLength + 1) { TreeSet<Int>() }

                repeat(1 shl (halfLength - 1)) { status ->
                    var count = 0
                    var sum = 0
                    status.forEachBit {
                        count++
                        sum += nums[offset + it]
                    }

                    sums[count].add(sum)
                    sums[halfLength - count].add(halfSum - sum)
                }

                return sums
            }

            val sum = nums.sum()
            val targetSum = sum / 2

            val leftSums = generateSums(0)
            val rightSums = generateSums(halfLength)

            var result = Int.MAX_VALUE

            leftSums.forEachIndexed { pickCount, lefts ->
                lefts.forEach { left ->
                    rightSums[halfLength - pickCount].also {
                        if (targetSum - left in it) {
                            result = result.coerceAtMost((sum - targetSum * 2).absoluteValue)
                        }

                        it.lower(targetSum - left)?.also {
                            result = result.coerceAtMost((sum - (left + it) * 2).absoluteValue)
                        }

                        it.higher(targetSum - left)?.also {
                            result = result.coerceAtMost((sum - (left + it) * 2).absoluteValue)
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumDifference(
            intArrayOf(3, 9, 7, 3)
        )
    }
}