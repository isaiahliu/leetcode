package p02xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun kthSmallest(root: TreeNode?, k: Int): Int {
            var result = 0

            fun TreeNode.walk(index: Int): Int? {
                var foundCount = 0

                left?.also {
                    it.walk(index)?.also {
                        foundCount += it
                    } ?: return null
                }

                if (foundCount == index) {
                    result = `val`
                    return null
                }

                foundCount++

                right?.also {
                    it.walk(index - foundCount)?.also {
                        foundCount += it
                    } ?: return null
                }

                return foundCount
            }

            root?.walk(k - 1)

            return result
        }
    }

    measureTimeMillis {
        Solution().kthSmallest(
            TreeNode(
                1,
                null,
                TreeNode(2)
            ), 2
        ).also { println(it) }
    }
}

