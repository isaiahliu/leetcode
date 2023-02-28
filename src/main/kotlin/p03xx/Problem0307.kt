package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class RangeTreeNode(nums: IntArray, private val range: IntRange) {
        var left: RangeTreeNode?
        var right: RangeTreeNode?

        private var _sum: Int? = null

        init {
            if (range.first == range.last) {
                left = null
                right = null
                _sum = nums[range.first]
            } else {
                left = RangeTreeNode(nums, range.first..range.first + (range.last - range.first) / 2)
                right = RangeTreeNode(nums, range.first + (range.last - range.first) / 2 + 1..range.last)
            }
        }

        private val sum: Int
            get() {
                return _sum ?: run {
                    ((left?.sum ?: 0) + (right?.sum ?: 0)).also { _sum = it }
                }
            }

        fun update(index: Int, value: Int) {
            if (index in range) {
                if (range.first == range.last) {
                    _sum = value
                } else {
                    _sum = null
                    left?.update(index, value)
                    right?.update(index, value)
                }
            }
        }

        fun sumRange(startIndex: Int, endIndex: Int): Int {
            return if (range.first >= startIndex && range.last <= endIndex) {
                sum
            } else if (range.first <= endIndex && range.last >= startIndex) {
                (left?.sumRange(startIndex, endIndex) ?: 0) + (right?.sumRange(startIndex, endIndex) ?: 0)
            } else {
                0
            }
        }
    }

    class NumArray(private val nums: IntArray) {
        val root: RangeTreeNode = RangeTreeNode(nums, nums.indices)

        fun update(index: Int, `val`: Int) {
            if (nums[index] != `val`) {
                nums[index] = `val`
                root.update(index, `val`)
            }
        }

        fun sumRange(left: Int, right: Int): Int {
            return if (left == right) {
                nums[left]
            } else if (left > right) {
                0
            } else {
                root.sumRange(left, right)
            }
        }
    }

    measureTimeMillis {
        val array = NumArray(intArrayOf(1, 3, 5))
        array.sumRange(0, 2).also { println(it) }
    }
}

