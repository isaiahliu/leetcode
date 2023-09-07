package p23xx

import util.expect

fun main() {
    class Solution {
        fun validSubarraySize(nums: IntArray, threshold: Int): Int {
            return when {
                threshold < nums.size -> {
                    nums.size
                }

                threshold < nums.max() -> {
                    1
                }

                else -> {

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

                    val groups = arrayOfNulls<Group>(nums.size)

                    for (maxIndex in groups.indices.sortedByDescending { nums[it] }) {
                        val num = nums[maxIndex]

                        val group = Group().also { it.size++ }
                        groups.getOrNull(maxIndex - 1)?.join(group)
                        groups.getOrNull(maxIndex + 1)?.join(group)

                        if (num > threshold / group.parent.size) {
                            return group.parent.size
                        }
                        groups[maxIndex] = group
                    }

                    -1
                }
            }
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