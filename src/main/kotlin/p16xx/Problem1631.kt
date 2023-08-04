package p16xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumEffortPath(heights: Array<IntArray>): Int {
            class Group {
                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
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

            val m = heights.size
            val n = heights[0].size

            val queue = PriorityQueue<Pair<Pair<Int, Int>, Int>>(compareBy { (_, cost) ->
                cost
            })

            fun cost(from: Int, to: Int): Int {
                return (heights[from / n][from % n] - heights[to / n][to % n]).let {
                    if (it < 0) {
                        -it
                    } else {
                        it
                    }
                }
            }

            val groups = Array(m * n) {
                val r = it / n
                val c = it % n

                if (r > 0) {
                    queue.add(it - n to it to cost(it - n, it))
                }
                if (c > 0) {
                    queue.add(it - 1 to it to cost(it - 1, it))
                }
                Group()
            }

            val start = groups[0]
            val end = groups[groups.lastIndex]
            var result = 0

            while (start.parent != end.parent) {
                val (pair, cost) = queue.poll()

                result = result.coerceAtLeast(cost)

                groups[pair.first].join(groups[pair.second])
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minimumEffortPath(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}