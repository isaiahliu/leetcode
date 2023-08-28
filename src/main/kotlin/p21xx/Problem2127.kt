package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumInvitations(favorite: IntArray): Int {
            val adjacent = Array(favorite.size) {
                hashSetOf<Int>()
            }

            favorite.forEachIndexed { index, num ->
                adjacent[num].add(index)
            }

            var result = 0
            var pairMax = 0

            val timestamp = IntArray(favorite.size) {
                Int.MAX_VALUE
            }

            val visited = hashSetOf<Int>()
            favorite.forEachIndexed { index, num ->
                val route = hashSetOf<Int>()

                var current = index
                var time = 0
                loop@ while (true) {
                    when {
                        current in visited -> {
                            break@loop
                        }

                        time < timestamp[current] -> {
                            timestamp[current] = time
                            route.add(current)
                        }

                        time - timestamp[current] == 2 -> {
                            route.clear()
                            route.add(current)
                            route.add(favorite[current])

                            val tasks = LinkedList<Int>()
                            tasks.add(current)
                            var route1 = 0
                            while (tasks.isNotEmpty()) {
                                repeat(tasks.size) {
                                    adjacent[tasks.poll()].forEach {
                                        if (route.add(it)) {
                                            tasks.add(it)
                                        }
                                    }
                                }

                                route1++
                            }

                            tasks.add(favorite[current])
                            var route2 = 0
                            while (tasks.isNotEmpty()) {
                                repeat(tasks.size) {
                                    adjacent[tasks.poll()].forEach {
                                        if (route.add(it)) {
                                            tasks.add(it)
                                        }
                                    }
                                }

                                route2++
                            }

                            pairMax += route1 + route2
                            break@loop
                        }

                        else -> {
                            result = result.coerceAtLeast(time - timestamp[current])
                            break@loop
                        }
                    }

                    current = favorite[current]
                    time++
                }

                visited.addAll(route)
            }

            return result.coerceAtLeast(pairMax)
        }
    }

    expect {
        Solution().maximumInvitations(
            intArrayOf(1, 0, 3, 2, 5, 6, 7, 4, 9, 8, 11, 10, 11, 12, 10)
        )
    }
}