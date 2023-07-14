package p12xx

import util.TreeNode
import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        FindElements(null).find(
            1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
