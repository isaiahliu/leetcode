package p11xx

import java.util.*
import util.expect

fun main() {
    class DinnerPlates(val capacity: Int) {
        val stacks = hashMapOf<Int, LinkedList<Int>>()
        var maxIndex = 0

        val hasSpaceIndices = TreeSet<Int>()
        val hasItemIndices = TreeSet<Int>(compareByDescending { it })

        private fun ensureStack(index: Int? = null): Pair<Int, LinkedList<Int>> {
            return if (index == null) {
                ensureStack(maxIndex++)
            } else {
                index to stacks.computeIfAbsent(index) { LinkedList<Int>() }.also {
                    hasSpaceIndices.add(index)

                }
            }
        }

        fun push(`val`: Int) {
            val (index, stack) = ensureStack(hasSpaceIndices.firstOrNull())
            stack.push(`val`)

            hasItemIndices.add(index)

            if (stack.size == capacity) {
                hasSpaceIndices.remove(index)
            }
        }

        fun pop(): Int {
            return hasItemIndices.firstOrNull()?.let { popAtStack(it) } ?: -1
        }

        fun popAtStack(index: Int): Int {
            if (index !in hasItemIndices) {
                return -1
            }

            val stack = ensureStack(index).second

            hasSpaceIndices.add(index)

            if (stack.size == 1) {
                hasItemIndices.remove(index)
                stacks.remove(index)
            }

            return stack.poll()
        }
    }

    expect {
        val dp = DinnerPlates(1)

        dp.push(1)
        dp.push(2)
        dp.popAtStack(1)
        dp.pop()
        dp.push(1)
        dp.push(2)
        dp.pop()
        dp.pop()
    }
}