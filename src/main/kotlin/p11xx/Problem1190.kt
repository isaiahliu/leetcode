package p11xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun reverseParentheses(s: String): String {
            abstract class AbstractNode {
                abstract val result: String
            }

            class BuilderNode : AbstractNode() {
                val builder: StringBuilder = StringBuilder()

                override val result: String get() = builder.toString()
            }

            class ReversedNode : AbstractNode() {
                val children = LinkedList<AbstractNode>()

                fun append(c: Char) {
                    ((children.peekLast() as? BuilderNode) ?: run {
                        BuilderNode().also { children.add(it) }
                    }).builder.append(c)
                }

                fun child(): ReversedNode {
                    return ReversedNode().also { children.add(it) }
                }

                override val result: String
                    get() = children.joinToString("") { it.result }.reversed()
            }

            val stack = LinkedList<ReversedNode>()
            stack.push(ReversedNode())

            s.forEach {
                when (it) {
                    '(' -> {
                        stack.push(stack.peek().child())
                    }

                    ')' -> {
                        stack.poll()
                    }

                    else -> {
                        stack.peek().append(it)
                    }
                }
            }

            return stack.peekLast().result.reversed()
        }
    }

    expect {
        Solution().reverseParentheses(
            "a(bcdefghijkl(mno)p)q"
        )
    }
}