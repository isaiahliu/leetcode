package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Node(var `val`: Boolean, var isLeaf: Boolean) {
        var topLeft: Node? = null
        var topRight: Node? = null
        var bottomLeft: Node? = null
        var bottomRight: Node? = null
    }

    class Solution {
        fun construct(grid: Array<IntArray>): Node {
            fun generate(r: Int, c: Int, width: Int): Node {
                if (width == 1) {
                    return Node(grid[r][c] == 1, true)
                } else {
                    val topLeft = generate(r, c, width / 2)
                    val topRight = generate(r, c + width / 2, width / 2)
                    val bottomLeft = generate(r + width / 2, c, width / 2)
                    val bottomRight = generate(r + width / 2, c + width / 2, width / 2)

                    var isLeaf = true
                    var value = 0

                    isLeaf = isLeaf && topLeft.isLeaf
                    isLeaf = isLeaf && topRight.isLeaf
                    isLeaf = isLeaf && bottomLeft.isLeaf
                    isLeaf = isLeaf && bottomRight.isLeaf

                    value += if (topLeft.`val`) 1 else 0
                    value += if (topRight.`val`) 1 else 0
                    value += if (bottomLeft.`val`) 1 else 0
                    value += if (bottomRight.`val`) 1 else 0

                    return if (isLeaf && value % 4 == 0) {
                        Node(value == 4, true)
                    } else {
                        Node(false, false).also {
                            it.topLeft = topLeft
                            it.topRight = topRight
                            it.bottomLeft = bottomLeft
                            it.bottomRight = bottomRight
                        }
                    }
                }
            }

            return generate(0, 0, grid.size)
        }
    }

    measureTimeMillis {
        Solution().construct(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


