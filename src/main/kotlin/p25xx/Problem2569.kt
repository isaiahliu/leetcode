package p25xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun handleQuery(nums1: IntArray, nums2: IntArray, queries: Array<IntArray>): LongArray {
            for (i in 1 until nums1.size) {
                nums1[i] += nums1[i - 1]
            }

            class SegNode(private val start: Int, private val end: Int) {
                var sum = nums1[end] - (nums1.getOrNull(start - 1) ?: 0)

                var nodeReversed = false

                val children: List<SegNode>? by lazy {
                    if (start < end) {
                        listOf(SegNode(start, (start + end) / 2), SegNode((start + end) / 2 + 1, end))
                    } else {
                        null
                    }
                }

                fun reverse(from: Int = start, to: Int = end): Int {
                    when {
                        from > end || to < start -> {
                        }

                        from <= start && to >= end -> {
                            nodeReversed = !nodeReversed
                            sum = end - start + 1 - sum
                        }

                        else -> {
                            if (nodeReversed) {
                                nodeReversed = false
                                children?.forEach {
                                    it.reverse()
                                }
                            }

                            sum = 0

                            children?.forEach {
                                sum += it.reverse(from, to)
                            }
                        }
                    }

                    return sum
                }
            }

            val root = SegNode(0, nums1.lastIndex)

            var sum = nums2.fold(0L) { a, b -> a + b }

            val result = arrayListOf<Long>()
            queries.forEach { (op, param1, param2) ->
                when (op) {
                    1 -> {
                        root.reverse(param1, param2)
                    }

                    2 -> {
                        sum += root.sum.toLong() * param1
                    }

                    3 -> {
                        result.add(sum)
                    }
                }
            }

            return result.toLongArray()
        }
    }


    measureTimeMillis {
        Solution().handleQuery(
            intArrayOf(0, 0, 0, 0, 1, 0, 1, 1, 1), intArrayOf(35, 29, 21, 34, 8, 48, 22, 43, 37), arrayOf(
                intArrayOf(1, 4, 7),
                intArrayOf(3, 0, 0),
                intArrayOf(2, 27, 0),
                intArrayOf(3, 0, 0),
                intArrayOf(1, 0, 3),
                intArrayOf(3, 0, 0),
                intArrayOf(2, 6, 0),
                intArrayOf(1, 3, 8),
                intArrayOf(2, 13, 0),
                intArrayOf(3, 0, 0),
                intArrayOf(3, 0, 0),
                intArrayOf(3, 0, 0),
                intArrayOf(2, 2, 0),
                intArrayOf(2, 28, 0),
                intArrayOf(3, 0, 0),
                intArrayOf(3, 0, 0),
                intArrayOf(2, 25, 0),
                intArrayOf(3, 0, 0),
                intArrayOf(3, 0, 0),
                intArrayOf(1, 2, 5)
            )
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

