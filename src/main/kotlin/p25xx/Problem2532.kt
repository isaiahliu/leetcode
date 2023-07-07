package p25xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findCrossingTime(n: Int, k: Int, time: Array<IntArray>): Int {
            val idle = PriorityQueue(
                compareBy<Pair<Int, Int>> { (_, side) -> side }
                    .thenByDescending { (staff, _) -> time[staff][0] + time[staff][2] }
                    .thenByDescending { (staff, _) -> staff })

            val events = PriorityQueue(
                compareBy<Pair<Int, Pair<Int, Int>>> { (_, actions) -> actions.second }
                    .thenBy { (_, actions) -> actions.first })
            var bridgeBusy = false
            var leftStaffCount = k
            var remainingBoxes = n

            val RIGHT = 0
            val LEFT = 1

            repeat(k) {
                idle.offer(it to LEFT)
            }

            var result = 0

            val PICK_OLD = 1
            val PUT_NEW = 2
            val MOVE_LEFT_TO_RIGHT = 3
            val MOVE_RIGHT_TO_LEFT = 4

            val debug = true

            fun pass() {
                if (bridgeBusy) {
                    return
                }

                idle.poll()?.also { (staff, side) ->
                    when (side) {
                        LEFT -> {
                            if (remainingBoxes > 0) {
                                events.offer(staff to (MOVE_LEFT_TO_RIGHT to result + time[staff][0]))

                                remainingBoxes--
                                leftStaffCount--
                                bridgeBusy = true
                            }
                        }

                        RIGHT -> {
                            events.offer(staff to (MOVE_RIGHT_TO_LEFT to result + time[staff][2]))
                            bridgeBusy = true
                        }
                    }
                }
            }

            while (leftStaffCount < k || remainingBoxes > 0) {
                events.poll()?.also { (staff, p) ->
                    val (nextAction, nextTime) = p

                    when (nextAction) {
                        PICK_OLD -> {
                            idle.offer(staff to RIGHT)
                        }

                        PUT_NEW -> {
                            idle.offer(staff to LEFT)
                        }

                        MOVE_LEFT_TO_RIGHT -> {
                            events.offer(staff to (PICK_OLD to nextTime + time[staff][1]))
                            bridgeBusy = false
                        }

                        MOVE_RIGHT_TO_LEFT -> {
                            events.offer(staff to (PUT_NEW to nextTime + time[staff][3]))
                            bridgeBusy = false
                            leftStaffCount++
                        }
                    }

                    result = nextTime
                }

                pass()
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findCrossingTime(
            8, 7, arrayOf(
                intArrayOf(6, 7, 5, 3),
                intArrayOf(5, 6, 10, 2),
                intArrayOf(10, 7, 4, 7),
                intArrayOf(9, 10, 6, 2),
                intArrayOf(8, 6, 3, 7),
                intArrayOf(8, 6, 9, 5),
                intArrayOf(3, 6, 9, 2)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
