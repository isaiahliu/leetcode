package p01xx

import java.util.*
import util.expect

fun main() {
    class MinStack {
        val stack = LinkedList<Int>()

        val numCounts = hashMapOf<Int, Int>()

        var minNum = 0
        var minCount = 0

        fun push(`val`: Int) {
            if (`val` == minNum) {
                minCount++
            } else if (`val` < minNum || minCount == 0) {
                minNum = `val`
                minCount++
            }

            numCounts[`val`] = (numCounts[`val`] ?: 0) + 1

            stack.push(`val`)
        }

        fun pop() {
            val top = stack.pop()

            if (numCounts[top] == 1) {
                numCounts.remove(top)

                minNum = numCounts.keys.minOrNull() ?: 0
                minCount = numCounts[minNum] ?: 0
            } else {
                numCounts[top] = (numCounts[top] ?: 0) - 1
            }
        }

        fun top(): Int {
            return stack.peek()
        }

        fun getMin(): Int {
            return minNum
        }
    }

    expect {
        val stack = MinStack()

        stack.push(Int.MAX_VALUE - 1)
        stack.push(Int.MAX_VALUE - 1)
        stack.push(Int.MAX_VALUE)
        stack.top()
        stack.pop()
        stack.getMin()
        stack.pop()
        stack.getMin()
        stack.pop()
        stack.push(Int.MAX_VALUE)
    }
}

