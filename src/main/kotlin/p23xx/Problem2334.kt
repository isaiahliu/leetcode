package p23xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun validSubarraySize(nums: IntArray, threshold: Int): Int {
            if (threshold < nums.size) {
                return nums.size
            } else if (threshold < nums.max()) {
                return 1
            }

            class Group {
                var size = 0

                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
                        value.size += size
                    }
                    get() {
                        return innerParent?.parent?.also {
                            innerParent = it
                        } ?: this
                    }

                fun join(target: Group) {
                    val leftParent = parent
                    val rightParent = target.parent

                    if (leftParent != rightParent) {
                        leftParent.parent = rightParent
                    }
                }
            }

            val groups = Array(nums.size) {
                Group().also { it.size++ }
            }

            val queue = PriorityQueue<Int>(compareByDescending { nums[it] })
            queue.addAll(groups.indices)

            while (queue.isNotEmpty()) {
                val maxIndex = queue.poll()
                val num = nums[maxIndex]

                if (num == 1) {
                    break
                }

                nums.getOrNull(maxIndex + 1)?.takeIf { it >= num }?.also {
                    groups[maxIndex].join(groups[maxIndex + 1])
                }

                nums.getOrNull(maxIndex - 1)?.takeIf { it >= num }?.also {
                    groups[maxIndex].join(groups[maxIndex - 1])
                }

                var requireSize = threshold / num
                while (num <= threshold / requireSize) {
                    requireSize++
                }

                if (requireSize <= groups[maxIndex].parent.size) {
                    return requireSize
                }
            }

            return -1
        }
    }

    expect {
        Solution().validSubarraySize(
            intArrayOf(
                3, 2, 3
            ), 4
        )
    }
}