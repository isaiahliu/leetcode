package p02xx

import java.util.*
import util.expect

fun main() {
    class MyQueue {
        val stack1 = LinkedList<Int>()
        val stack2 = LinkedList<Int>()

        fun push(x: Int) {
            while (stack1.isNotEmpty()) {
                stack2.push(stack1.pop())
            }

            stack2.push(x)
        }

        fun pop(): Int {
            while (stack2.isNotEmpty()) {
                stack1.push(stack2.pop())
            }

            return stack1.pop()
        }

        fun peek(): Int {
            while (stack2.isNotEmpty()) {
                stack1.push(stack2.pop())
            }

            return stack1.peek()
        }

        fun empty(): Boolean {
            return stack1.isEmpty() && stack2.isEmpty()
        }
    }

    expect {
        MyQueue()
    }
}

