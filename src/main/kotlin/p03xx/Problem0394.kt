package p03xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun decodeString(s: String): String {
            class Node(private val count: Int = 1) {
                var str: String? = null

                constructor(s: String) : this(1) {
                    str = s
                }

                val children = arrayListOf<Node>()

                val evaluate: String
                    get() {
                        return str ?: children.joinToString("") { it.evaluate }.repeat(count)
                    }
            }

            val root = Node(1)
            val stack = LinkedList<Node>()
            stack.push(root)

            var str = ""
            var count = 0
            s.forEach {
                if (it in 'a'..'z') {
                    str += it
                } else {
                    if (str.isNotEmpty()) {
                        stack.peek().children.add(Node(str))
                        str = ""
                    }

                    when (it) {
                        in '0'..'9' -> {
                            count *= 10
                            count += it - '0'
                        }

                        '[' -> {
                            val newNode = Node(count)
                            stack.peek().children.add(newNode)
                            stack.push(newNode)
                            count = 0
                        }

                        ']' -> {
                            stack.pop()
                        }
                    }
                }
            }

            if (str.isNotEmpty()) {
                stack.peek().children.add(Node(str))
                str = ""
            }

            return root.evaluate
        }
    }

    expect {
        Solution().decodeString(
            "3[a2[c]]"
        )
    }
}

