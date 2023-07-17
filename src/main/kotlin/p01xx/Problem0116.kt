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
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

