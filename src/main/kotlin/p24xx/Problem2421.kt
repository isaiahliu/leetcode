package p24xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun numberOfGoodPaths(vals: IntArray, edges: Array<IntArray>): Int {
            class Group(var maxNum: Int) {
                var size = 1

                fun add(num: Int, s: Int) {
                    when {
                        num < maxNum -> {
                            return
                        }

                        num > maxNum -> {
                            maxNum = num
                            size = s
                        }

                        else -> {
                            size += s
                        }
                    }
                }

                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
                        value.add(maxNum, size)
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

            val groups = Array(vals.size) { Group(vals[it]) }
            val queue = PriorityQueue<IntArray>(compareBy { vals[it[1]] })

            edges.forEach {
                if (vals[it[0]] > vals[it[1]]) {
                    it.reverse()
                }

                queue.add(it)
            }

            var result = vals.size

            while (queue.isNotEmpty()) {
                val (node1, node2) = queue.poll()

                if (groups[node1].parent.maxNum == vals[node2]) {
                    result += groups[node1].parent.size * groups[node2].parent.size
                }

                groups[node1].join(groups[node2])
            }

            return result
        }
    }

    expect {
        Solution().numberOfGoodPaths(
            intArrayOf(
                1, 4, 11, 19, 14, 11, 12, 18, 9, 15, 18, 9, 11, 1, 18, 8, 10, 13, 3, 17, 1, 10, 11, 15, 11, 19, 2
            ), arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(3, 0),
                intArrayOf(4, 3),
                intArrayOf(0, 5),
                intArrayOf(2, 6),
                intArrayOf(7, 4),
                intArrayOf(4, 8),
                intArrayOf(9, 2),
                intArrayOf(10, 0),
                intArrayOf(3, 11),
                intArrayOf(1, 12),
                intArrayOf(5, 13),
                intArrayOf(6, 14),
                intArrayOf(6, 15),
                intArrayOf(16, 0),
                intArrayOf(14, 17),
                intArrayOf(12, 18),
                intArrayOf(19, 6),
                intArrayOf(20, 17),
                intArrayOf(14, 21),
                intArrayOf(12, 22),
                intArrayOf(23, 20),
                intArrayOf(24, 11),
                intArrayOf(25, 15),
                intArrayOf(26, 7)
            )
        )
    }
}