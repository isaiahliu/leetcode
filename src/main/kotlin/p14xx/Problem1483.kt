package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Node(value: Int) {
        val values = arrayListOf(value)
        var parent: Int = -1
    }

    class TreeAncestor(n: Int, parent: IntArray) {
        val children: Array<Pair<Node, Int>?>

        init {
            val childrenValues = Array(n) { arrayListOf<Int>() }
            parent.forEachIndexed { index, i ->
                childrenValues.getOrNull(i)?.add(index)
            }

            children = arrayOfNulls(n)

            childrenValues.forEachIndexed { index, childNums ->
                if (childNums.size != 1) {
                    var current = index
                    var offset = 0

                    val node = Node(current)

                    children[current] = node to 0

                    while (true) {
                        current = parent[current]

                        if (current < 0) {
                            break
                        }

                        offset++

                        if (childrenValues[current].size == 1) {
                            node.values.add(current)
                            children[current] = node to offset
                        } else {
                            break
                        }
                    }

                    node.parent = current
                }
            }
        }

        fun getKthAncestor(node: Int, k: Int): Int {
            var (child, offset) = children[node] ?: return -1

            offset += k

            while (offset >= child.values.size) {
                offset -= child.values.size
                child = children.getOrNull(child.parent)?.first ?: return -1
            }

            return child.values[offset]
        }
    }

    measureTimeMillis {
        TreeAncestor(7, intArrayOf(-1, 0, 0, 5, 1, 1, 5))
            .getKthAncestor(3, 2).also {
                println(it)
            }
    }.also { println("Time cost: ${it}ms") }
}