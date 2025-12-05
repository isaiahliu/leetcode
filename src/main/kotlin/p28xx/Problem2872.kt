package p28xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maxKDivisibleComponents(n: Int, edges: Array<IntArray>, values: IntArray, k: Int): Int {
            class Group {
                var sum = 0L

                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
                        value.sum += sum
                    }
                    get() {
                        return innerParent?.parent?.also {
                            innerParent = it
                        } ?: this
                    }

                fun join(target: Group) {
                    val leftParent = parent
                    val rightParent = target.parent

                    if (leftParent != rightParent) {
                        leftParent.parent = rightParent
                    }
                }
            }

            val adjacent = Array(n) {
                hashSetOf<Int>()
            }

            val degrees = IntArray(n)
            edges.forEach { (from, to) ->
                adjacent[from].add(to)
                adjacent[to].add(from)

                degrees[from]++
                degrees[to]++
            }

            val groups = arrayOfNulls<Group>(n)

            val tasks = LinkedList<Int>()
            degrees.forEachIndexed { index, degree ->
                if (degree <= 1) {
                    tasks += index
                }
            }

            var result = 0
            while (tasks.isNotEmpty()) {
                val nodeIndex = tasks.poll()

                if (groups[nodeIndex] != null) {
                    continue
                }

                val group = Group().also { it.sum = values[nodeIndex].toLong() }
                groups[nodeIndex] = group

                adjacent[nodeIndex].forEach {
                    groups[it]?.also {
                        group.join(it)
                    } ?: run {
                        degrees[it]--
                        if (degrees[it] <= 1) {
                            tasks += it
                        }
                    }
                }

                if (group.parent.sum % k == 0L) {
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().maxKDivisibleComponents(
            1, arrayOf(
            ), intArrayOf(0), 1
        )
    }
}
