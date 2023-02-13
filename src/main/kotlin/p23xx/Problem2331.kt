package p23xx

fun main() {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    class Solution {
        fun TreeNode?.evaluate(): Boolean {
            return when (this?.`val`) {
                0, null -> false
                1 -> true
                2 -> left.evaluate() || right.evaluate()
                3 -> left.evaluate() && right.evaluate()
                else -> throw RuntimeException("Error")
            }
        }

        fun evaluateTree(root: TreeNode?): Boolean {
            return root.evaluate()
        }
    }
}
