package p00xx

import util.expect

fun main() {
    class Solution {
        fun search(nums: IntArray, target: Int): Boolean {
            val splitNum = nums[0]

            if (splitNum == target) {
                return true
            }

            fun findMaxNumIndex(leftIndex: Int, rightIndex: Int): Boolean {
                if (leftIndex > rightIndex) {
                    return false
                }

                val midIndex = leftIndex + (rightIndex - leftIndex) / 2
                return when (val midNum = nums[midIndex]) {
                    target -> return true
                    else -> when {
                        midNum > splitNum -> {
                            when (target) {
                                in (splitNum + 1) until midNum -> {
                                    //find left
                                    findMaxNumIndex(leftIndex, midIndex - 1)
                                }

                                else -> {
                                    //find right
                                    findMaxNumIndex(midIndex + 1, rightIndex)
                                }
                            }
                        }

                        midNum < splitNum -> {
                            when (target) {
                                in midNum + 1 until splitNum -> {
                                    //find right
                                    findMaxNumIndex(midIndex + 1, rightIndex)
                                }

                                else -> {
                                    //find left
                                    findMaxNumIndex(leftIndex, midIndex - 1)
                                }
                            }
                        }

                        else -> {
                            findMaxNumIndex(leftIndex, midIndex - 1) || findMaxNumIndex(midIndex + 1, rightIndex)
                        }
                    }
                }
            }

            return findMaxNumIndex(0, nums.lastIndex)
        }
    }

    expect {
        Solution().search(intArrayOf(1), 0)
    }
}

