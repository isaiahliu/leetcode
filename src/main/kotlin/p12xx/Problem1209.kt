package p12xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun removeDuplicates(s: String, k: Int): String {
            class Node(val char: Char) {
                var count: Int = 1
            }

            val stack = LinkedList<Node>()

            s.forEach {
                val last = stack.peekLast()
                when {
                    last?.char != it -> {
                        stack.add(Node(it))
                    }

                    else -> {
                        last.count = (last.count + 1) % k

                        if (last.count == 0) {
                            stack.pollLast()
                        }
                    }
                }
            }

            return stack.joinToString("") {
                it.char.toString().repeat(it.count)
            }
        }
    }

    measureTimeMillis {
        Solution().removeDuplicates(
            "krpgjbjjznpzdfy",
            14
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
