package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun validateBinaryTreeNodes(n: Int, leftChild: IntArray, rightChild: IntArray): Boolean {
            class Node {
                var root: Node = this
                    get() {
                        if (field != this && field != field.root) {
                            field = field.root
                        }
                        return field
                    }
            }

            val nodes = Array(n) { Node() }
            fun IntArray.fillParents(): Boolean {
                forEachIndexed { index, i ->
                    if (i >= 0) {
                        val parent = nodes[index]
                        val child = nodes[i]
                        when {
                            child.root != child -> {
                                return false
                            }

                            parent.root == child.root -> {
                                return false
                            }

                            else -> {
                                child.root = parent
                            }
                        }
                    }
                }

                return true
            }

            return leftChild.fillParents() && rightChild.fillParents() && nodes.map { it.root }.toSet().size == 1
        }
    }

    measureTimeMillis {
        Solution().validateBinaryTreeNodes(
            4,
            intArrayOf(1, 0, 3, -1),
            intArrayOf(-1, -1, -1, -1),
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

