package p21xx

import util.expect

fun main() {
    class Solution {
        fun subArrayRanges(nums: IntArray): Long {
            val sortedIndices = nums.indices.sortedBy { nums[it] }

            class SegNode(val range: IntRange) {
                val children = arrayOfNulls<SegNode>(2)

                fun init(index: Int): Long {
                    var result = 0L
                    if (children[0] == null) {
                        result = 1L * nums[index] * (index - range.first + 1) * (range.last - index + 1)

                        children[0] = SegNode(range.first until index)
                        children[1] = SegNode(index + 1..range.last)
                    } else {
                        children.forEach {
                            it?.also {
                                if (index in it.range) {
                                    result += it.init(index)
                                }
                            }
                        }
                    }

                    return result
                }
            }

            val minRoot = SegNode(nums.indices)
            val maxRoot = SegNode(nums.indices)

            var result = 0L
            sortedIndices.indices.forEach {
                result += maxRoot.init(sortedIndices[sortedIndices.lastIndex - it])
                result -= minRoot.init(sortedIndices[it])
            }

            return result
        }
    }

    expect {
        Solution().subArrayRanges(
            intArrayOf(4, -2, -3, 4, 1)
        )
    }
}