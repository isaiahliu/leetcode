package p02xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
            root ?: return null
            val pValue = p?.`val` ?: return null
            val qValue = q?.`val` ?: return null

            var result: TreeNode? = null

            fun TreeNode.walk() {
                when {
                    pValue > `val` && qValue > `val` -> {
                        right?.walk()
                    }

                    pValue < `val` && qValue < `val` -> {
                        left?.walk()
                    }

                    else -> {
                        result = this
                    }
                }
            }

            root.walk()

            return result
        }
    }

    measureTimeMillis {
        Solution().lowestCommonAncestor(null, null, null).also { println(it) }
    }
}

