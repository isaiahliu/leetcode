package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumDetonation(bombs: Array<IntArray>): Int {
            val adjacent = Array(bombs.size) {
                hashSetOf<Int>()
            }

            for (i in bombs.indices) {
                val (x1, y1, r1) = bombs[i]
                for (j in i + 1 until bombs.size) {
                    val (x2, y2, r2) = bombs[j]

                    val distance = 1L * (x2 - x1) * (x2 - x1) + 1L * (y2 - y1) * (y2 - y1)
                    if (distance <= 1L * r1 * r1) {
                        adjacent[i].add(j)
                    }

                    if (distance <= 1L * r2 * r2) {
                        adjacent[j].add(i)
                    }
                }
            }

            var result = 0
            bombs.indices.forEach {
                val visited = hashSetOf(it)
                val tasks = LinkedList<Int>()
                tasks.add(it)

                while (tasks.isNotEmpty()) {
                    tasks.poll().also {
                        adjacent[it].forEach {
                            if (visited.add(it)) {
                                tasks.add(it)
                            }
                        }
                    }
                }

                result = result.coerceAtLeast(visited.size)
            }

            return result
        }
    }

    expect {
        Solution().maximumDetonation(
            arrayOf()
        )
    }
}