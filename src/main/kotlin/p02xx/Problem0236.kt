package p02xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
            root ?: return null
            p ?: return null
            q ?: return null

            var result: TreeNode? = null

            fun TreeNode.walk(target: TreeNode): Boolean {
                if (result != null) {
                    return false
                }

                var found = this == target

                if (!found) {
                    found = left?.walk(target) ?: false
                }

                if (!found) {
                    found = right?.walk(target) ?: false
                }

                if (found) {
                    if (target == p && walk(q)) {
                        result = this
                    }
                }

                return found
            }

            root.walk(p)

            return result
        }
    }

    expect {
        Solution().lowestCommonAncestor(null, null, null)
    }
}

