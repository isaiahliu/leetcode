package p01xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Node(var `val`: Int, var left: Node? = null, var right: Node? = null) {
        var next: Node? = null
    }

    class Solution {
        fun connect(root: Node?): Node? {
            val nodes = LinkedList<Node>()

            var size = 0
            root?.let {
                nodes.add(it)
                size++
            }


            while (size > 0) {
                var pre: Node? = null
                repeat(size.also { size = 0 }) {
                    val top = nodes.pop()

                    pre?.next = top
                    pre = top

                    top.left?.also {
                        nodes.add(it)
                        size++
                    }
                    top.right?.also {
                        nodes.add(it)
                        size++
                    }
                }
            }

            return root
        }
    }

    measureTimeMillis {
        Solution().connect(
            Node(
                0,
                Node(
                    2, Node(1, Node(5), Node(1))
                ),
                Node(
                    4, Node(3, null, Node(6)), Node(-1, null, Node(8))
                ),
            )
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

