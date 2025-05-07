package p33xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minTimeToReach(moveTime: Array<IntArray>): Int {
            val visited = hashSetOf(0 to 0)

            val queue = PriorityQueue<Pair<Pair<Int, Int>, Int>>(compareBy { it.second })
            queue.add(0 to 0 to 0)

            while (queue.isNotEmpty()) {
                val (pos, time) = queue.poll()

                when {
                    pos.first == moveTime.lastIndex && pos.second == moveTime[0].lastIndex -> return time
                    else -> {
                        arrayOf(-1 to 0, 0 to -1, 0 to 1, 1 to 0).forEach { (dr, dc) ->
                            moveTime.getOrNull(pos.first + dr)?.getOrNull(pos.second + dc)?.also {
                                if (visited.add(pos.first + dr to pos.second + dc)) {
                                    queue.add(pos.first + dr to pos.second + dc to (maxOf(time, it) + 1 + (pos.first + pos.second) % 2))
                                }
                            }
                        }
                    }
                }
            }

            return -1
        }
    }

    expect {
        Solution().minTimeToReach(
            arrayOf()
        )
    }
}
