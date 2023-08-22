package p20xx

import util.expect

fun main() {
    class Solution {
        fun waysToPartition(nums: IntArray, k: Int): Int {
            val leftIndices = hashMapOf<Long, MutableSet<Int>>()
            val rightIndices = hashMapOf<Long, MutableSet<Int>>()

            var leftSum = 0L
            var rightSum = 0L

            nums.forEachIndexed { index, num ->
                rightIndices.computeIfAbsent(num.toLong()) { hashSetOf() }.add(index)
                rightSum += num
            }

            val results = IntArray(nums.size)
            var noop = 0
            for (index in 0 until nums.lastIndex) {
                val num = nums[index].toLong()
                leftIndices.computeIfAbsent(num) { hashSetOf() }.add(index)
                rightIndices[num]?.also {
                    if (it.size == 1) {
                        rightIndices.remove(num)
                    } else {
                        it.remove(index)
                    }
                }

                rightSum -= num
                leftSum += num

                if (leftSum == rightSum) {
                    noop++
                }

                val delta = rightSum - leftSum

                // leftSum - num + k == rightSum -> num = k - delta
                leftIndices[k - delta]?.forEach {
                    results[it]++
                }

                // rightSum - num + k == leftSum -> num = delta + k
                rightIndices[k + delta]?.forEach {
                    results[it]++
                }
            }

            return results.max().coerceAtLeast(noop)
        }
    }

    expect {
        Solution().waysToPartition(
            intArrayOf(2, -1, 2), 3
        )
    }
}