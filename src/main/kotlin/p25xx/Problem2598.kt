package p25xx

import util.expect

fun main() {
    class Solution {
        fun findSmallestInteger(nums: IntArray, value: Int): Int {
            class SegNode(val from: Int, val to: Int) {
                val children by lazy {
                    arrayOf(
                        SegNode(from, from + (to - from) / 2),
                        SegNode(from + (to - from) / 2 + 1, to)
                    )
                }

                var markSize = 0

                fun mark(num: Long): Boolean {
                    return when {
                        num > to || num < from -> false
                        markSize == to - from + 1 -> false
                        from == to -> {
                            markSize++
                            true
                        }

                        else -> {
                            children.any { it.mark(num) }.also {
                                if (it) {
                                    markSize++
                                }
                            }
                        }
                    }
                }

                fun min(): Int? {
                    return when (markSize) {
                        0 -> from
                        to - from + 1 -> null
                        else -> children[0].min() ?: children[1].min()
                    }
                }
            }

            val root = SegNode(0, nums.size - 1)

            val counts = hashMapOf<Int, Int>()
            nums.forEach {
                val num = it.mod(value)

                val count = counts[num] ?: 0
                counts[num] = count + 1

                root.mark(
                    num + count * value.toLong()
                )
            }

            return root.min() ?: nums.size
        }
    }

    expect {
        Solution().findSmallestInteger(
            intArrayOf(1, -10, 7, 13, 6, 8), 7
        )
    }
}