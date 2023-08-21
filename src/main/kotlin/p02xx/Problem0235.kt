package p02xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
            root ?: return null
            val pValue = p?.`val` ?: return null
            val qValue = q?.`val` ?: return null

            var result: TreeNode? = null

            fun TreeNode.walk() {
                when {
                    pValue > `val` && qValue > `val` -> {
                        right?.walk()
                    }

                    pValue < `val` && qValue < `val` -> {
                        left?.walk()
                    }

                    else -> {
                        result = this
                    }
                }
            }

            root.walk()

            return result
        }
    }

    expect {
        Solution().lowestCommonAncestor(null, null, null)
    }
}

