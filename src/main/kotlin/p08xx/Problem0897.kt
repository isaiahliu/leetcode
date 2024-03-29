package p08xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun increasingBST(root: TreeNode?): TreeNode? {
            fun TreeNode.dfs(): Pair<TreeNode, TreeNode> {
                var head = this
                var tail = this

                left?.dfs()?.also { (r, t) ->
                    head = r
                    t.right = this
                }

                right?.dfs()?.also { (r, t) ->
                    right = r
                    tail = t
                }

                left = null

                return head to tail
            }

            return root?.dfs()?.first
        }
    }

    expect {
        Solution().increasingBST(
            null
        )
    }
}