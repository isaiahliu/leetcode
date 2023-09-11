package p24xx

import util.expect

fun main() {
    class Solution {
        fun numberOfPairs(nums1: IntArray, nums2: IntArray, diff: Int): Long {
            class SegNode(val start: Int, val end: Int) {
                val children by lazy {
                    val mid = start + (end - start) / 2

                    arrayOf(
                        SegNode(start, mid),
                        SegNode(mid + 1, end),
                    )
                }

                var size = 0

                fun add(num: Int) {
                    when {
                        num !in start..end -> {

                        }

                        start == end -> {
                            size++
                        }

                        else -> {
                            size++
                            children.forEach {
                                it.add(num)
                            }
                        }
                    }
                }

                fun query(max: Int): Long {
                    return when {
                        max < start -> 0L
                        max >= end -> size.toLong()
                        size == 0 -> 0
                        else -> children.sumOf { it.query(max) }
                    }
                }
            }

            val root =
                SegNode(nums1.indices.minOf { nums1[it] - nums2[it] }, nums1.indices.maxOf { nums1[it] - nums2[it] })
            var result = 0L
            nums1.indices.forEach {
                val d = nums1[it] - nums2[it]

                result += root.query(d + diff)
                root.add(d)
            }

            return result
        }
    }

    expect {
        Solution().numberOfPairs(
            intArrayOf(3, 2, 5), intArrayOf(2, 2, 1), 1
        )
    }
}