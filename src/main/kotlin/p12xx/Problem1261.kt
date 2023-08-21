package p12xx

import util.TreeNode
import util.expect

fun main() {
    class FindElements(root: TreeNode?) {
        val visited = hashSetOf<Int>()

        fun TreeNode.dfs() {
            visited.add(`val`)

            left?.also {
                it.`val` = `val` * 2 + 1
                it.dfs()
            }

            right?.also {
                it.`val` = `val` * 2 + 2
                it.dfs()
            }
        }

        init {
            root?.also {
                it.`val` = 0

                it.dfs()
            }
        }

        fun find(target: Int): Boolean {
            return target in visited
        }
    }

    expect {
        FindElements(null).find(
            1
        )
    }
}
