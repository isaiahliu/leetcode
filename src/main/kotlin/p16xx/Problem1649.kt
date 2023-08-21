package p16xx

import util.expect

fun main() {
    class Solution {
        fun createSortedArray(instructions: IntArray): Int {
            val leftLimit = instructions.min()
            val rightLimit = instructions.max()

            class SegNode(val start: Int, val end: Int) {
                var size = 0

                val left: SegNode by lazy {
                    SegNode(start, (start + end) / 2)
                }

                val right: SegNode by lazy {
                    SegNode((start + end) / 2 + 1, end)
                }

                fun insert(num: Int) {
                    if (num in start..end) {
                        size++
                        if (start < end) {
                            val leftRange = start..(start + end) / 2

                            if (num in leftRange) {
                                left.insert(num)
                            } else {
                                right.insert(num)
                            }
                        }
                    }
                }

                fun countToLeft(rightNum: Int): Int {
                    return when {
                        size == 0 -> {
                            0
                        }

                        rightNum >= end -> {
                            size
                        }

                        rightNum < start -> {
                            0
                        }

                        else -> {
                            left.countToLeft(rightNum) + right.countToLeft(rightNum)
                        }
                    }
                }

                fun countToRight(leftNum: Int): Int {
                    return when {
                        size == 0 -> {
                            0
                        }

                        leftNum <= start -> {
                            size
                        }

                        leftNum > end -> {
                            0
                        }

                        else -> {
                            left.countToRight(leftNum) + right.countToRight(leftNum)
                        }
                    }
                }
            }

            val m = 1000000007
            val root = SegNode(leftLimit, rightLimit)
            var result = 0L
            instructions.forEach {
                result += root.countToLeft(it - 1).coerceAtMost(root.countToRight(it + 1))
                result %= m

                root.insert(it)
            }

            return result.toInt()
        }
    }

    expect {
        Solution().createSortedArray(
            intArrayOf(1, 3, 2)
        )
    }
}