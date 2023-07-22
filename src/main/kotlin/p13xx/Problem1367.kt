package p13xx

import util.ListNode
import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isSubPath(head: ListNode?, root: TreeNode?): Boolean {
            head ?: return true
            var result = false
            val visited = hashSetOf<Pair<TreeNode, ListNode>>()

            fun TreeNode.dfs(list: ListNode) {
                if (result) {
                    return
                }

                if (!visited.add(this to list)) {
                    return
                }

                if (list.`val` == `val`) {
                    list.next?.also {
                        left?.dfs(it)
                        right?.dfs(it)
                    } ?: run { result = true }

                    left?.takeIf { it.`val` == head.`val` }?.also { it.dfs(head) }
                    right?.takeIf { it.`val` == head.`val` }?.also { it.dfs(head) }
                } else {
                    left?.dfs(head)
                    right?.dfs(head)
                }
            }

            root?.dfs(head)

            return result
        }
    }

    measureTimeMillis {
        Solution().isSubPath(
            null, null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

