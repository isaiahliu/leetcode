package p01xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sortedArrayToBST(nums: IntArray): TreeNode? {
            fun build(leftIndex: Int, rightIndex: Int): TreeNode? {
                if (leftIndex > rightIndex) {
                    return null
                }

                val midIndex = leftIndex + (rightIndex - leftIndex) / 2

                val root = TreeNode(nums[midIndex])

                root.left = build(leftIndex, midIndex - 1)
                root.right = build(midIndex + 1, rightIndex)

                return root
            }

            return build(0, nums.lastIndex)
        }
    }

    measureTimeMillis {
        Solution().sortedArrayToBST(
            intArrayOf(1)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

