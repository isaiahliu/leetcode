package p25xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maxPoints(grid: Array<IntArray>, queries: IntArray): IntArray {
            class Group {
                var size = 0

                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
                        value.size += size
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

            val groups = Array(grid.size) {
                arrayOfNulls<Group>(grid[it].size)
            }

            val nums = PriorityQueue<Pair<Int, Int>>(compareBy { grid[it.first][it.second] })
            grid.indices.forEach { r ->
                grid[r].indices.forEach { c ->
                    nums.add(r to c)
                }
            }

            val result = IntArray(queries.size)

            queries.indices.sortedBy { queries[it] }.forEach { index ->
                val query = queries[index]

                while (nums.isNotEmpty() && nums.peek().let { grid[it.first][it.second] } < query) {
                    val (r, c) = nums.poll()

                    val group = Group()
                    group.size++

                    groups[r][c] = group

                    groups.getOrNull(r - 1)?.getOrNull(c)?.join(group)
                    groups.getOrNull(r + 1)?.getOrNull(c)?.join(group)
                    groups.getOrNull(r)?.getOrNull(c - 1)?.join(group)
                    groups.getOrNull(r)?.getOrNull(c + 1)?.join(group)
                }

                result[index] = groups[0][0]?.parent?.size ?: 0
            }

            return result
        }
    }

    expect {
        Solution().maxPoints(arrayOf(), intArrayOf())
    }
}