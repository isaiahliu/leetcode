package p09xx

import util.TreeNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun verticalTraversal(root: TreeNode?): List<List<Int>> {
            val result = TreeMap<Int, TreeMap<Int, MutableList<Int>>>()

            fun TreeNode.dfs(r: Int, c: Int) {
                result.computeIfAbsent(c) { TreeMap() }.computeIfAbsent(r) {
                    arrayListOf()
                }.add(`val`)

                left?.dfs(r + 1, c - 1)
                right?.dfs(r + 1, c + 1)
            }
            root?.dfs(0, 0)

            return result.values.map {
                it.values.map { it.sorted() }.flatten()
            }
        }
    }

    measureTimeMillis {
        Solution().verticalTraversal(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
