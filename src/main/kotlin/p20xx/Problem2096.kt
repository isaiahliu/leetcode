package p20xx

import util.TreeNode
import util.expect
import java.util.*

fun main() {
    class Solution {
        fun getDirections(root: TreeNode?, startValue: Int, destValue: Int): String {
            var startRoute = ""
            var endRoute = ""
            var found = 0
            val stack = LinkedList<TreeNode>()
            stack.push(root)

            val route = StringBuilder()

            while (found < 0b11) {
                val top = stack.peek()
                when (top.`val`) {
                    startValue -> {
                        startRoute = route.toString()
                        found = found or 0b01
                    }

                    destValue -> {
                        endRoute = route.toString()
                        found = found or 0b10
                    }
                }

                val left = top.left
                val right = top.right

                when {
                    left != null -> {
                        top.left = null
                        stack.push(left)
                        route.append("L")
                    }

                    right != null -> {
                        top.right = null
                        stack.push(right)
                        route.append("R")
                    }

                    else -> {
                        stack.poll()
                        route.deleteCharAt(route.lastIndex)
                    }
                }
            }

            var index = 0
            while (true) {
                val ch1 = startRoute.getOrNull(index) ?: break
                endRoute.getOrNull(index)?.takeIf { it == ch1 } ?: break
                index++
            }

            return "${"U".repeat(startRoute.length - index)}${endRoute.drop(index)}"
        }
    }

    expect {
        Solution().getDirections(
            TreeNode(
                7,
                TreeNode(8),
                TreeNode(
                    3,
                    TreeNode(4),
                    TreeNode(5)
                )
            ), 7, 5
        )
    }
}