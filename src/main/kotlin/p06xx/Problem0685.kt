package p06xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findRedundantDirectedConnection(edges: Array<IntArray>): IntArray {
            class Node(val value: Int) {
                val children = hashMapOf<Int, Node>()

                fun addChild(seq: Int, child: Node) {
                    children[seq] = child
                }
            }

            val nodes = hashMapOf<Int, Node>()
            val roots = hashSetOf<Node>()

            val result = intArrayOf(0, 0)
            edges.forEachIndexed { index, (from, to) ->
                val fromNode = nodes.computeIfAbsent(from) { Node(from).also { roots.add(it) } }
                val toNode = nodes.computeIfAbsent(to) { Node(to) }

                roots.remove(toNode)
                fromNode.addChild(index, toNode)

                if (roots.isEmpty()) {
                    if (result[0] == 0) {
                        result[0] = from
                        result[1] = to
                    }
                } else {
                    result[0] = 0
                }
            }

            if (roots.isEmpty()) {
                return result
            }

            val stack = LinkedList<Pair<Node, List<Int>>>()
            stack.push(roots.first() to emptyList())

            val visited = hashMapOf(stack.peek().first.value to 0)

            while (stack.isNotEmpty()) {
                val (last, route) = stack.pop()

                for ((index, child) in last.children) {
                    if (child.value in route) {
                        return intArrayOf(last.value, child.value)
                    }
                    visited[child.value]?.also {
                        return edges[index.coerceAtLeast(it)]
                    } ?: run {
                        stack.push(child to route + child.value)
                        visited[child.value] = index
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findRedundantDirectedConnection(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4),
                intArrayOf(4, 1),
                intArrayOf(1, 5),
            )
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}