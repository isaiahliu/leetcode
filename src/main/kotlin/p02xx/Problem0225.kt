package p02xx

import util.expect
import java.util.*

fun main() {
    class MyStack {
        val stack = LinkedList<Int>()
        fun push(x: Int) {
            stack.add(x)
        }

        fun pop(): Int {
            return stack.pollLast()
        }

        fun top(): Int {
            return stack.peekLast()
        }

        fun empty(): Boolean {
            return stack.isEmpty()
        }
    }

    expect { }
}

