package p23xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun closestMeetingNode(edges: IntArray, node1: Int, node2: Int): Int {
            fun Int.distance(): IntArray {
                val result = IntArray(edges.size) { edges.size }

                val queue = LinkedList<Int>()
                queue.add(this)

                var d = 0
                while (queue.isNotEmpty()) {
                    repeat(queue.size) {
                        val node = queue.poll()

                        if (result[node] > d) {
                            result[node] = d

                            edges[node].takeIf { it >= 0 }?.also {
                                queue.add(it)
                            }
                        }
                    }

                    d++
                }

                return result
            }

            val distance1 = node1.distance()
            val distance2 = node2.distance()

            return edges.indices.filter { distance1[it] < edges.size && distance2[it] < edges.size }.minByOrNull {
                distance1[it].coerceAtLeast(distance2[it])
            } ?: -1
        }
    }

    expect {
        Solution().closestMeetingNode(
            intArrayOf(1, 2, 3, 1), 1, 2
        )
    }
}