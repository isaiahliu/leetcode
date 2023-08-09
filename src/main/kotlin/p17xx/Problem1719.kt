package p17xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkWays(pairs: Array<IntArray>): Int {
            val connections = hashMapOf<Int, MutableSet<Int>>()

            pairs.forEach { (from, to) ->
                connections.computeIfAbsent(from) { hashSetOf() }.add(to)
                connections.computeIfAbsent(to) { hashSetOf() }.add(from)
            }

            var moreThanOne = false

            val root = connections.entries.firstOrNull { it.value.size == connections.size - 1 } ?: return 0
            connections.remove(root.key)
            connections.forEach { it.value.remove(root.key) }

            val stack = LinkedList<Set<Int>>()
            stack.add(root.value)

            while (connections.isNotEmpty()) {
                val parent = stack.peek()

                val nextParent = parent.maxBy { connections[it]?.size ?: -1 }

                val nextChildren = connections[nextParent]

                if (nextChildren == null) {
                    stack.poll() ?: return 0
                    continue
                }

                if (!parent.containsAll(nextChildren)) {
                    return 0
                }

                if (nextChildren.size == parent.size - 1) {
                    moreThanOne = true
                }

                connections.remove(nextParent)
                connections.forEach { it.value.remove(nextParent) }

                if (nextChildren.isNotEmpty()) {
                    stack.push(nextChildren)
                }
            }

            return if (moreThanOne) 2 else 1
        }
    }

    measureTimeMillis {
        Solution().checkWays(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
            )
        ).also { println("${it} should be $it") }

        Solution().checkWays(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(1, 3),
            )
        ).also { println("${it} should be $it") }

        Solution().checkWays(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(2, 4),
                intArrayOf(1, 5)
            )
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
