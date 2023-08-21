package p03xx

import java.util.*
import util.expect

fun main() {
    open class NestedInteger {
        // Constructor initializes an empty nested list.
        constructor()

        // Constructor initializes a single integer.
        constructor(value: Int)

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        fun isInteger(): Boolean {
            return true
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        fun getInteger(): Int? {
            return null
        }

        // Set this NestedInteger to hold a single integer.
        fun setInteger(value: Int): Unit {}

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        fun add(ni: NestedInteger): Unit {}

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        fun getList(): List<NestedInteger>? {
            return null
        }

    }

    class Solution {
        fun deserialize(s: String): NestedInteger {
            val root = NestedInteger()

            val stack = LinkedList<NestedInteger>()
            stack.add(root)

            var curr = root

            var num = ""
            s.forEach {
                if (it in '0'..'9' || it == '-') {
                    num += it
                } else {
                    if (num.isNotEmpty()) {
                        curr.add(NestedInteger(num.toInt()))
                        num = ""
                    }

                    when (it) {
                        '[' -> {
                            val newItem = NestedInteger()
                            curr.add(newItem)

                            stack.push(curr)
                            curr = newItem
                        }

                        ']' -> {
                            curr = stack.pop()
                        }

                    }
                }
            }

            if (num.isNotEmpty()) {
                curr.add(NestedInteger(num.toInt()))
            }

            return root.getList()!![0]
        }
    }

    expect {
        Solution().deserialize("[123,456,[788,799,833],[[]],10,[]]")
    }
}

