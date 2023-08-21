package p01xx

import util.ListNode
import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun sortedArrayToBST(nums: List<Int>): TreeNode? {
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

        fun sortedListToBST(head: ListNode?): TreeNode? {
            var t = head
            val nums = arrayListOf<Int>()

            while (t != null) {
                nums.add(t.`val`)
                t = t.next
            }

            return sortedArrayToBST(nums)
        }
    }

    expect {
        Solution().sortedListToBST(
            null
        )
    }
}

