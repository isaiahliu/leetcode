package p01xx

import util.expect

fun main() {
    class Node(var `val`: Int) {
        var next: Node? = null
        var random: Node? = null
    }

    class Solution {
        val map = hashMapOf<Node?, Node>()

        fun copyRandomList(node: Node?): Node? {
            return map[node] ?: run {
                node?.let {
                    val cloneNode = Node(it.`val`)

                    map[it] = cloneNode

                    cloneNode.next = copyRandomList(it.next)
                    cloneNode.random = copyRandomList(it.random)

                    cloneNode
                }
            }
        }
    }

    expect {
        Solution().copyRandomList(
            null
        )
    }
}

