package p34xx

import util.expect

fun main() {
    class Solution {
        fun numOfUnplacedFruits(fruits: IntArray, baskets: IntArray): Int {
            class SegNode(val left: Int, val right: Int) {
                var max: Int = 0

                val children by lazy {
                    arrayOf(SegNode(left, (left + right) / 2), SegNode((left + right) / 2 + 1, right))
                }

                fun mark(index: Int, value: Int) {
                    if (index < left || index > right) {
                        return
                    }

                    max = maxOf(max, value)

                    if (left < right) {
                        children.forEach {
                            it.mark(index, value)
                        }
                    }
                }

                fun remove(value: Int): Int? {
                    if (value > max) {
                        return null
                    }

                    if (left == right) {
                        max = -1
                        return left
                    } else {
                        val result = children[0].remove(value) ?: children[1].remove(value)

                        max = children.maxOf { it.max }

                        return result
                    }
                }
            }

            val root = SegNode(0, baskets.lastIndex)

            baskets.forEachIndexed { index, b ->
                root.mark(index, b)
            }

            var result = 0

            fruits.forEach { f ->
                root.remove(f) ?: result++
            }

            return result
        }
    }

    expect {
        Solution().numOfUnplacedFruits(
            intArrayOf(0, 17), intArrayOf(14, 19)
        )
    }
}
