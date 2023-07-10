package p11xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxDepthAfterSplit(seq: String): IntArray {
            var max = 0

            val depths = intArrayOf(0, 0)

            val stack = LinkedList<Int>()

            fun push(index: Int): Int {
                stack.push(index)
                depths[index]++
                return index
            }
            return IntArray(seq.length) {
                when {
                    seq[it] == ')' -> {
                        stack.poll().also {
                            depths[it]--
                        }
                    }

                    depths[1] < depths[0] -> {
                        push(1)
                    }

                    depths[0] < max -> {
                        push(0)
                    }

                    else -> {
                        max++
                        push(0)
                    }
                }
            }
        }
    }

    measureTimeMillis {
        Solution().maxDepthAfterSplit(
            "(()())"
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}