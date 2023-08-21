package p05xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun outerTrees(trees: Array<IntArray>): Array<IntArray> {
            if (trees.size <= 3) {
                return trees
            }

            fun check(first: Int, second: Int, target: Int): Boolean {
                val (x1, y1) = trees[first]
                val (x2, y2) = trees[second]

                val deltaX = x2 - x1
                val deltaY = y2 - y1

                val (x, y) = trees[target]

                return when (deltaX) {
                    0 -> x == x1 || deltaY > 0 && x < x1 || deltaY < 0 && x > x1
                    else -> (deltaY * x + deltaX * y1 - deltaY * x1) <= deltaX * y
                }
            }

            val remaining = TreeSet(compareBy<Int> { trees[it][0] }.thenByDescending { trees[it][1] })

            remaining.addAll(trees.indices)

            val forwardStack = LinkedList<Int>()

            var current = remaining.pollFirst() ?: return trees
            while (true) {
                if (forwardStack.size < 2 || check(forwardStack[1], forwardStack[0], current)) {
                    forwardStack.push(current)
                    current = remaining.higher(current) ?: break
                    remaining.remove(current)
                } else {
                    remaining.add(forwardStack.poll())
                }
            }

            val backwardStack = LinkedList<Int>()

            remaining.add(forwardStack.pollLast())
            backwardStack.push(forwardStack.poll())

            current = remaining.pollLast() ?: return trees
            while (true) {
                if (backwardStack.size < 2 || check(backwardStack[1], backwardStack[0], current)) {
                    backwardStack.push(current)
                    current = remaining.pollLast() ?: break
                } else {
                    backwardStack.poll()
                }
            }

            return (forwardStack + backwardStack).map { trees[it] }.toTypedArray()
        }
    }

    expect {
        Solution().outerTrees(
            arrayOf(
                intArrayOf(3, 0),
                intArrayOf(4, 0),
                intArrayOf(5, 0),
                intArrayOf(6, 1),
                intArrayOf(7, 2),
                intArrayOf(7, 3),
                intArrayOf(7, 4),
                intArrayOf(6, 5),
                intArrayOf(5, 5),
                intArrayOf(4, 5),
                intArrayOf(3, 5),
                intArrayOf(2, 5),
                intArrayOf(1, 4),
                intArrayOf(1, 3),
                intArrayOf(1, 2),
                intArrayOf(2, 1),
                intArrayOf(4, 2),
                intArrayOf(0, 3)
            )
        ).map { it.toList() }

    }
}