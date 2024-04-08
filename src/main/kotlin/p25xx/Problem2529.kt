package p25xx

import util.expect

fun main() {
    class Solution {
        fun maximumCount(nums: IntArray): Int {
            var negRight = -1
            var posLeft = nums.size

            tailrec fun findPos(left: Int, right: Int) {
                if (left > right) {
                    return
                }

                val mid = (left + right) / 2

                if (nums[mid] == 0) {
                    findPos(mid + 1, right)
                } else {
                    posLeft = mid

                    findPos(left, mid - 1)
                }
            }

            tailrec fun findNeg(left: Int, right: Int) {
                if (left > right) {
                    return
                }

                val mid = (left + right) / 2

                if (nums[mid] == 0) {
                    findNeg(left, mid - 1)
                } else {
                    negRight = mid

                    findNeg(mid + 1, right)
                }
            }

            tailrec fun findBoth(left: Int, right: Int) {
                if (left > right) {
                    return
                }

                val mid = (left + right) / 2
                when {
                    nums[mid] < 0 -> {
                        negRight = mid

                        findBoth(mid + 1, right)
                    }

                    nums[mid] > 0 -> {
                        posLeft = mid

                        findBoth(left, mid - 1)
                    }

                    else -> {
                        findNeg(left, mid - 1)
                        findPos(mid + 1, right)
                    }
                }
            }

            findBoth(0, nums.lastIndex)

            return maxOf(nums.size - posLeft, negRight + 1)
        }
    }

    expect {
        Solution().maximumCount(
            intArrayOf(-1, -1, 0, 1)
        )
    }
}

