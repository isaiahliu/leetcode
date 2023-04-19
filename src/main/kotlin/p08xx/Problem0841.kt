package p08xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().canVisitAllRooms(
            listOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}