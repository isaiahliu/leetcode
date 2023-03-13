package p04xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
            val top = TreeNode(Int.MAX_VALUE)
            top.left = root

            var setChild: TreeNode.(TreeNode?) -> Unit = {
                this.left = it
            }

            var parent = top
            var t = root
            while (t != null && t.`val` != key) {
                parent = t
                t = if (key > t.`val`) {
                    setChild = { this.right = it }
                    t.right
                } else {
                    setChild = { this.left = it }
                    t.left
                }
            }

            t ?: return top.left

            when {
                t.left == null && t.right == null -> {
                    parent.setChild(null)
                }

                t.left == null -> {
                    parent.setChild(t.right)
                }

                t.right == null -> {
                    parent.setChild(t.left)
                }

                else -> {
                    val left = t.left

                    parent.setChild(left)

                    var l = left
                    while (l?.right != null) {
                        l = l.right
                    }

                    l?.right = t.right
                }
            }

            return top.left
        }
    }

    measureTimeMillis {
        Solution().deleteNode(
            TreeNode(
                10,
                TreeNode(
                    3,
                    TreeNode(
                        1,
                        TreeNode(0),
                        TreeNode(2)
                    ),
                    TreeNode(
                        5,
                        TreeNode(4),
                        TreeNode(6)
                    )
                ),
                TreeNode(20),
            ), 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}