package p31xx

import util.expect

fun main() {
    class Solution {
        fun maximumSumSubsequence(nums: IntArray, queries: Array<IntArray>): Int {
            nums.forEachIndexed { index, num ->
                if (num < 0) {
                    nums[index] = 0
                }
            }

            class SegNode(val left: Int, val right: Int) {
                var sums = LongArray(4)

                val children: Array<SegNode> by lazy {
                    arrayOf(
                        SegNode(left, (left + right) / 2), SegNode((left + right) / 2 + 1, right)
                    )
                }

                fun recalculate(dirtyIndex: Int?) {
                    dirtyIndex?.also {
                        if (it !in left..right) {
                            return
                        }
                    }

                    if (left == right) {
                        sums[0b11] = nums[left].toLong()
                    } else {
                        children.forEach {
                            it.recalculate(dirtyIndex)
                        }

                        sums.indices.forEach { mark ->
                            sums[mark] = maxOf(
                                children[0].sums[mark and 0b10] + children[1].sums[mark and 0b01],
                                children[0].sums[mark and 0b10 or 0b01] + children[1].sums[mark and 0b01],
                                children[0].sums[mark and 0b10] + children[1].sums[mark and 0b01 or 0b10],
                            )
                        }
                    }
                }
            }

            val root = SegNode(0, nums.lastIndex)
            root.recalculate(null)

            return (queries.sumOf { (pos, num) ->
                maxOf(num, 0).also {
                    if (nums[pos] != it) {
                        nums[pos] = it
                        root.recalculate(pos)
                    }
                }

                root.sums.max()
            } % 1000000007).toInt()
        }
    }

    expect {
        Solution().maximumSumSubsequence(
            intArrayOf(3, 5, 9), arrayOf(
                intArrayOf(1, -2), intArrayOf(0, -3)
            )
        )
    }
}
