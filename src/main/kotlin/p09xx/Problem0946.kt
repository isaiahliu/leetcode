package p09xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
class Solution {
    fun validateStackSequences(pushed: IntArray, popped: IntArray): Boolean {
        var pushIndex = 0
        var popIndex = 0

        val stack = LinkedList<Int>()

        while (pushIndex < pushed.size || popIndex < popped.size) {
            if (popped[popIndex] == stack.poll()) {
                popIndex++
                stack.pop()
                continue
            }

            if (pushIndex < pushed.size) {
                stack.push(pushed[pushIndex++])
                continue
            }

            return false
        }

        return true
    }
}


    measureTimeMillis {
        Solution().validateStackSequences(
            intArrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
