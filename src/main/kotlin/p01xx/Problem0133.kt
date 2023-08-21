package p01xx

import util.expect

fun main() {
    class Node(var `val`: Int) {
        var neighbors: ArrayList<Node?> = arrayListOf()
    }

    class Solution {
        val map = hashMapOf<Node, Node>()

        fun cloneGraph(node: Node?): Node? {
            return node?.let { n ->
                map[n] ?: run {
                    val cloneNode = Node(n.`val`)
                    map[n] = cloneNode

                    n.neighbors.forEach {
                        cloneNode.neighbors.add(it?.let { cloneGraph(it) })
                    }

                    cloneNode
                }
            }
        }
    }

    expect {
        Solution().cloneGraph(
            null
        )
    }
}

