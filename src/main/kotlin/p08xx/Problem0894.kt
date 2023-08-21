package p08xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        val cache = hashMapOf<Int, List<TreeNode>>()

        fun allPossibleFBT(n: Int): List<TreeNode?> {
            if (n % 2 == 0) {
                return emptyList()
            }

            if (n == 1) {
                return listOf(TreeNode(0))
            }

            if (n in cache) {
                return cache[n].orEmpty()
            }

            return (1 until n step 2).map { leftCount ->
                val rightCount = n - leftCount - 1

                allPossibleFBT(leftCount).map { l ->
                    allPossibleFBT(rightCount).map { r ->
                        TreeNode(0).also {
                            it.left = l
                            it.right = r
                        }
                    }
                }.flatten()
            }.flatten().also {
                cache[n] = it
            }
        }
    }

    expect {
        Solution().allPossibleFBT(
            5
        )
    }
}