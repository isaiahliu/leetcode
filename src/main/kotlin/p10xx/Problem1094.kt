package p10xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
            val PICKUP = 1
            val DROP = 2
            val queue = PriorityQueue(compareBy<Pair<Int, Int>> { (index, type) ->
                trips[index][type]
            }.thenByDescending { it.second })

            repeat(trips.size) {
                queue.add(it to PICKUP)
            }

            var c = capacity
            while (queue.isNotEmpty()) {
                val (index, type) = queue.poll()

                val (person, _, to) = trips[index]
                when (type) {
                    PICKUP -> {
                        c -= person

                        queue.add(index to DROP)

                        if (c < 0) {
                            return false
                        }
                    }

                    DROP -> {
                        c += person
                    }
                }
            }

            return true
        }
    }

    expect {
        Solution().carPooling(
            arrayOf(), 1
        )
    }
}
