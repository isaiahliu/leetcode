package p08xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun sumOfDistancesInTree(n: Int, edges: Array<IntArray>): IntArray {
            val connected = Array(n) { hashSetOf<Int>() }

            val childrenCount = IntArray(n)
            val result = IntArray(n)

            val leaves = hashSetOf<Int>()

            fun add(from: Int, to: Int) {
                connected[from].add(to)

                when (connected[from].size) {
                    1 -> leaves.add(from)
                    2 -> leaves.remove(from)
                }
            }

            fun remove(leaf: Int, parent: Int) {
                connected[parent].remove(leaf)

                if (connected[parent].size == 1) {
                    leaves.add(parent)
                }
            }

            edges.forEach { (from, to) ->
                add(from, to)
                add(to, from)
            }

            val stack = LinkedList<Pair<Int, Int>>()
            loop@ while (leaves.isNotEmpty()) {
                for (leaf in leaves.toSet().also { leaves.clear() }) {
                    val parent = connected[leaf].firstOrNull() ?: break@loop

                    remove(leaf, parent)

                    childrenCount[parent] += childrenCount[leaf] + 1
                    result[parent] += result[leaf] + childrenCount[leaf] + 1

                    stack.push(leaf to parent)
                }
            }

            while (stack.isNotEmpty()) {
                val (leaf, parent) = stack.poll()

                result[leaf] += result[parent] - childrenCount[leaf] - 1 - result[leaf] + n - childrenCount[leaf] - 1
            }

            return result
        }
    }

    expect {
        Solution().sumOfDistancesInTree(
            5, arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4),
            )
        ).toList()
    }
}