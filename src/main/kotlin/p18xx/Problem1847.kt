package p18xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun closestRoom(rooms: Array<IntArray>, queries: Array<IntArray>): IntArray {
            class SegNode(val min: Int, val max: Int) {
                val children = arrayOfNulls<SegNode>(2)

                val roomIds = TreeSet<Int>()

                fun registerRoom(room: IntArray) {
                    roomIds.add(room[0])

                    if (min < max) {
                        val mid = (min + max) / 2

                        if (room[1] > mid) {
                            children[1] ?: run {
                                SegNode(mid + 1, max).also { children[1] = it }
                            }
                        } else {
                            children[0] ?: run {
                                SegNode(min, mid).also { children[0] = it }
                            }
                        }.registerRoom(room)
                    }
                }

                fun query(query: IntArray): Int? {
                    val (preferId, minSize) = query

                    val ids = hashSetOf<Int>()
                    if (minSize <= min) {
                        roomIds.ceiling(preferId)?.also { ids.add(it) }
                        roomIds.floor(preferId)?.also { ids.add(it) }
                    } else if (minSize <= max) {
                        children[0]?.query(query)?.also { ids.add(it) }
                        children[1]?.query(query)?.also { ids.add(it) }
                    }

                    return ids.sortedWith(compareBy<Int> { if (it > preferId) it - preferId else preferId - it }.thenBy { it })
                        .firstOrNull()
                }
            }

            val sizes = rooms.map { it[1] }
            val minSize = sizes.min()
            val maxSize = sizes.max()

            val root = SegNode(minSize, maxSize)

            rooms.forEach {
                root.registerRoom(it)
            }

            return queries.map {
                root.query(it) ?: -1
            }.toIntArray()
        }
    }

    expect {
        Solution().closestRoom(
            arrayOf(
                intArrayOf(2, 2),
                intArrayOf(1, 2),
                intArrayOf(3, 2),
            ), arrayOf(
                intArrayOf(3, 1),
                intArrayOf(3, 3),
                intArrayOf(5, 2),
            )
        ).toList()

    }
}
