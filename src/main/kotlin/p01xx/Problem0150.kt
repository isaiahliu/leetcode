package p01xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun evalRPN(tokens: Array<String>): Int {
            val stack = LinkedList<Int>()
            tokens.forEach {
                val calculator: (Int, Int) -> Int = when (it) {
                    "+" -> {
                        { b, a -> a + b }
                    }

                    "-" -> {
                        { b, a -> a - b }
                    }

                    "*" -> {
                        { b, a -> a * b }
                    }

                    "/" -> {
                        { b, a -> a / b }
                    }

                    else -> {
                        stack.push(it.toInt())

                        return@forEach
                    }
                }

                stack.push(calculator(stack.pop(), stack.pop()))
            }

            return stack.pop()
        }
    }

    measureTimeMillis {
        Solution().evalRPN(
            emptyArray()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

