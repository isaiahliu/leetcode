package p04xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun findSubsequences(nums: IntArray): List<List<Int>> {
            class Node(val num: Int) {
                val children = TreeMap<Int, Node>()

                fun addNode(node: Node) {
                    if (node.num !in children) {
                        children[node.num] = node
                    }
                }

                private var list: List<List<Int>>? = null

                fun generateList(): List<List<Int>> {
                    return list ?: run {
                        val me = listOf(num)
                        arrayListOf<List<Int>>().also { l ->
                            children.values.forEach {
                                l.addAll(it.generateList().map { me + it })
                                l.add(me + listOf(it.num))
                            }


                            list = l
                        }
                    }
                }
            }

            val nodes = TreeMap<Int, LinkedList<Node>>()

            nums.map { Node(it) }.forEach { node ->
                nodes.headMap(node.num, true).values.forEach {
                    it.forEach { it.addNode(node) }
                }

                nodes.computeIfAbsent(node.num) { LinkedList() }.add(node)
            }

            return nodes.values.map { it.first().generateList() }.flatten()
        }
    }

    expect {
        Solution().findSubsequences(
            intArrayOf(1, 1, 1, 2, 2, 2)
        )
    }
}