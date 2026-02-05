package p36xx

import util.expect

fun main() {
    class Solution {
        fun maxSumTrionic(nums: IntArray): Long {
            val groups = arrayListOf<MutableList<LongArray>>()

            var group = arrayListOf<LongArray>()
            groups += group

            var sum = 0L
            for (i in 1 until nums.size) {
                val pre = nums[i - 1]
                val cur = nums[i]

                when {
                    cur == pre -> {
                        group = arrayListOf()
                        groups += group
                    }

                    cur < pre && group.isEmpty() -> Unit
                    cur > pre -> {
                        if (group.size % 2 == 0) {
                            group.lastOrNull()?.also {
                                it[0] -= pre
                            }

                            group.add(longArrayOf(Long.MIN_VALUE / 2, Long.MIN_VALUE / 2))
                            sum = pre.toLong()
                        }

                        sum += cur
                        group.last().also {
                            it[0] = maxOf(it[0], sum)
                            it[1] = maxOf(it[1] + cur, pre.toLong() + cur)
                        }
                    }

                    else -> {
                        if (group.size % 2 == 1) {
                            group.add(longArrayOf(0L, 0L))
                        }

                        group.last().also {
                            it[0] += cur
                        }
                    }
                }
            }

            return groups.maxOf { group ->
                var result = Long.MIN_VALUE / 2

                for (i in 2 until group.size step 2) {
                    result = maxOf(result, group[i - 2][1] + group[i - 1][0] + group[i][0])
                }

                result
            }
        }
    }

    expect {
        Solution().maxSumTrionic(
            intArrayOf(0, -2, -1, -3, 0, 2, -1)
        )
    }
}
