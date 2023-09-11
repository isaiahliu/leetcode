package p24xx

import util.TreeNode
import util.expect
import java.util.*

fun main() {
    class Solution {
        fun reverseOddLevels(root: TreeNode?): TreeNode? {
            val values = hashMapOf<Int, LinkedList<Int>>()

            fun TreeNode.read(depth: Int) {
                if (depth % 2 == 1) {
                    values.computeIfAbsent(depth) { LinkedList() }.add(`val`)
                }

                left?.read(depth + 1)
                right?.read(depth + 1)
            }

            root?.read(0)

            fun TreeNode.fill(depth: Int) {
                if (depth % 2 == 1) {
                    values[depth]?.pollLast()?.also {
                        `val` = it
                    }
                }

                left?.fill(depth + 1)
                right?.fill(depth + 1)
            }

            root?.fill(0)

            return root
        }
    }

    expect {
        Solution().reverseOddLevels(
            null
        )
    }
}