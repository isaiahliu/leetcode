package p13xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestZigZag(root: TreeNode?): Int {
            val cache = hashMapOf<Pair<TreeNode, Boolean>, Int>()

            fun TreeNode.dfs(direction: Boolean): Int {
                val cacheKey = this to direction

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = 1

                if (direction) {
                    left?.dfs(false)
                    right?.dfs(false)
                } else {
                    right?.dfs(true)
                    left?.dfs(true)
                }?.also {
                    result += it
                }

                cache[cacheKey] = result
                return result
            }

            root?.dfs(true)
            root?.dfs(false)
            return cache.values.max()
        }
    }

    measureTimeMillis {
        Solution().longestZigZag(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

