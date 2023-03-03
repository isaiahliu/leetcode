package p03xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class NestedInteger {
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
        fun setInteger(value: Int): Unit {

        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        fun add(ni: NestedInteger): Unit {

        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        fun getList(): List<NestedInteger>? {
            return null
        }
    }

    class NestedIterator(private val nestedList: List<NestedInteger>) {
        private var index = 0

        val stack = LinkedList<NestedInteger>()

        fun scanNext() {
            while (stack.isNotEmpty()) {
                if (stack.peek().isInteger()) {
                    break
                } else {
                    stack.pop().getList()?.also {
                        stack.addAll(0, it)
                    }
                }
            }
        }

        init {
            stack.addAll(nestedList)
            scanNext()
        }

        fun next(): Int {
            val result = stack.pop().getInteger() ?: 0

            scanNext()

            return result
        }

        fun hasNext(): Boolean {
            return stack.isNotEmpty()
        }
    }

    measureTimeMillis {
        NestedIterator(emptyList()).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

