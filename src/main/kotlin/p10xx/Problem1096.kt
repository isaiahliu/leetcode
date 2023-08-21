package p10xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun braceExpansionII(expression: String): List<String> {
            class Node(private val value: String? = null) {
                var children = arrayListOf<Node>()
                val childrenGroup = arrayListOf<List<Node>>(children)

                fun newGroup() {
                    if (children.isNotEmpty()) {
                        children = arrayListOf()
                        childrenGroup.add(children)
                    }
                }

                val expValues: Set<String>
                    get() {
                        if (value != null) {
                            return setOf(value)
                        }

                        val result = hashSetOf<String>()

                        childrenGroup.forEach {
                            val childrenValues = it.map { it.expValues }

                            if (childrenValues.isNotEmpty()) {
                                result.addAll(childrenValues.reduce { acc, strings ->
                                    acc.map { l -> strings.map { r -> "${l}${r}" } }.flatten().toSet()
                                })
                            }
                        }

                        return result
                    }
            }

            val root = Node()

            val stack = LinkedList<Node>()
            stack.push(root)

            var curr = root
            var index = 0
            var s = ""
            while (index < expression.length) {
                val c = expression[index++]

                if (c in 'a'..'z') {
                    s += c
                } else {
                    if (s.isNotEmpty()) {
                        curr.children.add(Node(s))
                        s = ""
                    }

                    when (c) {
                        ',' -> {
                            curr.newGroup()
                        }

                        '{' -> {
                            val newNode = Node()
                            curr.children.add(newNode)
                            stack.push(curr)
                            curr = newNode
                        }

                        '}' -> {
                            curr = stack.pop()
                        }
                    }
                }
            }
            if (s.isNotEmpty()) {
                curr.children.add(Node(s))
            }
            return root.expValues.sorted()
        }
    }

    expect {
        Solution().braceExpansionII(
            "{a,b}c{d,e}f"
        )
    }
}

