package p00xx

import util.expect

fun main() {
    class Solution {
        fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
            fun IntArray.binSearch(minIndex: Int, maxIndex: Int, target: Int): Int? {
                if (minIndex > maxIndex) {
                    return null
                }

                val midIndex = (minIndex + maxIndex) / 2

                return if (this[midIndex] < target) {
                    binSearch(midIndex + 1, maxIndex, target) ?: midIndex
                } else {
                    binSearch(minIndex, midIndex - 1, target)
                }
            }

            fun find(minIndex1: Int, maxIndex1: Int, minIndex2: Int, maxIndex2: Int): Int {
                var result = 0
                val target = (maxIndex1 - minIndex1 + 1 + maxIndex2 - minIndex2) / 2

                var left = nums1.getOrElse(minIndex1) { Int.MAX_VALUE }
                    .coerceAtMost(nums2.getOrElse(minIndex2) { Int.MAX_VALUE })
                var right = nums1.getOrElse(maxIndex1) { Int.MIN_VALUE }
                    .coerceAtLeast(nums2.getOrElse(maxIndex2) { Int.MIN_VALUE })

                while (left <= right) {
                    val mid = (left + right) / 2

                    val lessCount = (nums1.binSearch(minIndex1, maxIndex1, mid)?.let { it - minIndex1 + 1 }
                        ?: 0) + (nums2.binSearch(minIndex2, maxIndex2, mid)?.let { it - minIndex2 + 1 } ?: 0)

                    if (lessCount <= target) {
                        result = mid

                        left = mid + 1
                    } else {
                        right = mid - 1
                    }
                }
                return result
            }

            return if ((nums1.size + nums2.size) % 2 == 1) {
                find(0, nums1.lastIndex, 0, nums2.lastIndex).toDouble()
            } else {
                var sum = 0.0
                sum += when {
                    nums1.isEmpty() -> {
                        find(0, nums1.lastIndex, 1, nums2.lastIndex)
                    }

                    nums2.isEmpty() -> {
                        find(1, nums1.lastIndex, 0, nums2.lastIndex)
                    }

                    nums1[0] > nums2[0] -> {
                        find(0, nums1.lastIndex, 1, nums2.lastIndex)
                    }

                    else -> {
                        find(1, nums1.lastIndex, 0, nums2.lastIndex)
                    }
                }

                sum += when {
                    nums1.isEmpty() -> {
                        find(0, nums1.lastIndex, 0, nums2.lastIndex - 1)
                    }

                    nums2.isEmpty() -> {
                        find(0, nums1.lastIndex - 1, 0, nums2.lastIndex)
                    }

                    nums1[nums1.lastIndex] < nums2[nums2.lastIndex] -> {
                        find(0, nums1.lastIndex, 0, nums2.lastIndex - 1)
                    }

                    else -> {
                        find(0, nums1.lastIndex - 1, 0, nums2.lastIndex)
                    }
                }

                return sum / 2
            }
        }
    }

    expect(0.0) {
        Solution().findMedianSortedArrays(intArrayOf(0), intArrayOf(-1, 0, 1))
    }

    expect {
        Solution().findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2))
    }
}

