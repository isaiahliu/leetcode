package p15xx

import java.util.*
import kotlin.math.absoluteValue
import util.expect

fun main() {
    class Solution {
        fun minCostConnectPoints(points: Array<IntArray>): Int {
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

            val groups = Array(points.size) { Group().also { it.size++ } }

            val queue = PriorityQueue<Pair<Pair<Int, Int>, Int>>(compareBy { it.second })
            for (i in points.indices) {
                val (x1, y1) = points[i]

                for (j in i + 1 until points.size) {
                    val (x2, y2) = points[j]

                    queue.offer(i to j to (x1 - x2).absoluteValue + (y1 - y2).absoluteValue)
                }
            }

            var result = 0
            while (groups[0].parent.size < points.size) {
                queue.poll()?.also { (p, distance) ->
                    val (from, to) = p

                    if (groups[from].parent != groups[to].parent) {
                        groups[from].join(groups[to])
                        result += distance
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minCostConnectPoints(
            arrayOf(
                intArrayOf(1, 3, 2), intArrayOf(2, 3, 0), intArrayOf(1, 3, 0), intArrayOf(0, 2, 1)
            )
        )
    }
}

