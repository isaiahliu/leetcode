package p05xx

import util.expect

fun main() {
    class Node(var `val`: Boolean, var isLeaf: Boolean) {
        var topLeft: Node? = null
        var topRight: Node? = null
        var bottomLeft: Node? = null
        var bottomRight: Node? = null
    }

    class Solution {
        fun intersect(quadTree1: Node?, quadTree2: Node?): Node? {
            quadTree1 ?: return quadTree2
            quadTree2 ?: return quadTree1

            if (quadTree1.isLeaf) {
                return if (quadTree1.`val`) {
                    quadTree1
                } else {
                    quadTree2
                }
            }

            if (quadTree2.isLeaf) {
                return if (quadTree2.`val`) {
                    quadTree2
                } else {
                    quadTree1
                }
            }

            val result = Node(false, false)

            result.topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft)
            result.topRight = intersect(quadTree1.topRight, quadTree2.topRight)
            result.bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft)
            result.bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight)

            var trueCount = 0
            var leafCount = 0
            arrayOf(result.topLeft, result.topRight, result.bottomLeft, result.bottomRight).filterNotNull().forEach {
                if (it.isLeaf) {
                    leafCount++
                }

                if (it.`val`) {
                    trueCount++
                }
            }

            if (leafCount == 4 && trueCount % 4 == 0) {
                result.topLeft = null
                result.topRight = null
                result.bottomLeft = null
                result.bottomRight = null

                result.isLeaf = true
                result.`val` = trueCount > 0
            }

            return result
        }
    }

    expect {
        Solution().intersect(
            null, null
        )
    }
}