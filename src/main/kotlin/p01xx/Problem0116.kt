package p01xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Node(var `val`: Int) {
        var left: Node? = null
        var right: Node? = null
        var next: Node? = null
    }

    class Solution {
        fun connect(root: Node?): Node? {
            val nodes = LinkedList<Node>()
            root?.let { nodes.add(it) }

            while (nodes.isNotEmpty()) {
                var pre: Node? = null
                repeat(nodes.size) {
                    val top = nodes.pop()

                    pre?.next = top
                    pre = top

                    top.left?.also { nodes.add(it) }
                    top.right?.also { nodes.add(it) }
                }
            }

            return root
        }
    }

    measureTimeMillis {
        Solution().connect(
            null
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

