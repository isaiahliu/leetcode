package p08xx

import util.expect

fun main() {
    class Solution {
        fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
            val visited = hashSetOf(0)

            val keys = hashSetOf(0)

            while (keys.isNotEmpty()) {
                keys.toSet().also { keys.clear() }.forEach {
                    rooms[it].forEach {
                        if (visited.add(it)) {
                            keys.add(it)
                        }
                    }
                }
            }

            return visited.size == rooms.size
        }
    }

    expect {
        Solution().canVisitAllRooms(
            listOf()
        )
    }
}