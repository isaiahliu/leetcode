package p04xx

import util.expect

fun main() {
    class Node(var `val`: Int) {
        var prev: Node? = null
        var next: Node? = null
        var child: Node? = null
    }

    class Solution {
        fun flatten(root: Node?): Node? {
            fun Node.flattenInternal(): Node {
                var cur = this
                var tail = cur

                while (true) {
                    tail = cur
                    val next = cur.next
                    cur.child?.also {
                        tail = it.flattenInternal()

                        cur.next = it
                        it.prev = cur
                        cur.child = null
                        tail.next = next
                        next?.prev = tail
                    }

                    cur = next ?: break
                }

                return tail
            }

            return root?.also { it.flattenInternal() }
        }
    }

    expect {
        val n1 = Node(1)
        val n2 = Node(2)
        val n3 = Node(3)

        n1.child = n2
        n2.child = n3
        Solution().flatten(
            n1
        )
    }
}


