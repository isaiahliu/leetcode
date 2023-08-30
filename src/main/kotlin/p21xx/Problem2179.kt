package p21xx

import util.expect

fun main() {
    class Solution {
        fun goodTriplets(nums1: IntArray, nums2: IntArray): Long {
            class SegNode(val start: Int, val end: Int) {
                val children = arrayOfNulls<SegNode>(2)

                init {
                    if (start < end) {
                        val mid = (start + end) / 2
                        children[0] = SegNode(start, mid)
                        children[1] = SegNode(mid + 1, end)
                    }
                }

                var size = 0

                fun query(num: Int): Int {
                    return when {
                        num > end -> {
                            size
                        }

                        num < start -> {
                            0
                        }

                        else -> {
                            size++
                            children.sumOf {
                                it?.query(num) ?: 0
                            }
                        }
                    }
                }
            }

            val indices = IntArray(nums2.size)

            nums2.forEachIndexed { index, num ->
                indices[num] = index
            }

            var result = 0L
            val root = SegNode(0, nums1.lastIndex)

            nums1.forEachIndexed { index, i ->
                val pos = indices[i]

                root.query(pos).let { lower ->
                    val higher = nums1.lastIndex - pos - (index - lower)

                    result += 1L * lower * higher
                }
            }

            return result
        }
    }

    expect {
        Solution().goodTriplets(
            intArrayOf(2, 0, 1, 3), intArrayOf(0, 1, 2, 3)
        )
    }
//    val params = input.map {
//        it.split(",").map { it.toInt() }.toIntArray()
//    }
//    expect {
//        Solution().goodTriplets(
//            params[0], params[1]
//        )
//    }
}