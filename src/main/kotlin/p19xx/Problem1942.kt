package p19xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun smallestChair(times: Array<IntArray>, targetFriend: Int): Int {
            val chairs = TreeSet<Int>()
            repeat(times.size) {
                chairs.add(it)
            }

            val use = IntArray(times.size)

            val queue = PriorityQueue(compareBy<Pair<Int, Pair<Int, Int>>> { it.first }.thenBy { it.second.second })

            times.forEachIndexed { index, (join, leave) ->
                queue.add(join to (index to 1))
                queue.add(leave to (index to 0))
            }

            while (queue.isNotEmpty()) {
                val (index, event) = queue.poll().second

                if (index == targetFriend) {
                    return chairs.first()
                }

                when (event) {
                    0 -> {
                        chairs.add(use[index])
                    }

                    1 -> {
                        use[index] = chairs.pollFirst() ?: 0
                    }
                }
            }

            return 0
        }
    }

    expect {
        Solution().smallestChair(
            arrayOf(
                intArrayOf(3, 10),
                intArrayOf(1, 5),
                intArrayOf(2, 6),
            ), 0
        )
    }
}