package p12xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minRemoveToMakeValid(s: String): String {
            val availableIndices = hashSetOf<Int>()

            val stack = LinkedList<Int>()
            s.forEachIndexed { index, c ->
                when (c) {
                    '(' -> {
                        stack.push(index)
                    }

                    ')' -> {
                        stack.poll()?.also {
                            availableIndices.add(index)
                            availableIndices.add(it)
                        }
                    }

                }
            }

            val result = StringBuilder()
            s.forEachIndexed { index, c ->
                when (c) {
                    '(', ')' -> {
                        if (index in availableIndices) {
                            result.append(c)
                        }
                    }

                    else -> {
                        result.append(c)
                    }
                }
            }
            return result.toString()
        }
    }

    measureTimeMillis {
        Solution().minRemoveToMakeValid(
            ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

