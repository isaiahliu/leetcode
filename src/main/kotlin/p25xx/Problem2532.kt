package p25xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findCrossingTime(n: Int, k: Int, time: Array<IntArray>): Int {
            val waitingLeft =
                PriorityQueue(compareByDescending<Int> { time[it][0] + time[it][2] }.thenByDescending { it })
            val carryOld = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
            val putNew = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
            var passBridge: Pair<Int, Pair<Int, Int>>? = null
            val waitingRight =
                PriorityQueue(compareByDescending<Int> { time[it][0] + time[it][2] }.thenByDescending { it })

            var remainingBoxes = n

            repeat(k) {
                waitingLeft.add(it)
            }

            var currentTime = 0

            val NO_ACTION = 0
            val MOVE_RIGHT_TO_LEFT = 1
            val PASS_BRIDGE = 1
            val CARRY_OLD = 2
            val PUT_NEW = 3

            val LEFT_TO_RIGHT = 100
            val RIGHT_TO_LEFT = 101

            val debug = false

            fun pass() {
                when {
                    passBridge != null -> {

                    }

                    waitingRight.isNotEmpty() -> {
                        val staff = waitingRight.poll()

                        passBridge = (currentTime + time[staff][2]) to (staff to RIGHT_TO_LEFT)

                        if (debug) {
                            println("${currentTime} to ${currentTime + time[staff][2]}: Staff ${staff} moving right to left")
                        }
                    }

                    waitingLeft.isNotEmpty() && remainingBoxes > 0 -> {
                        val staff = waitingLeft.poll()

                        passBridge = (currentTime + time[staff][0]) to (staff to LEFT_TO_RIGHT)

                        remainingBoxes--

                        if (debug) {
                            println("${currentTime} to ${currentTime + time[staff][0]}: Staff ${staff} moving left to right")
                        }
                    }
                }
            }

            loop@ while (true) {
                var action = NO_ACTION
                var nextTime = Int.MAX_VALUE

                carryOld.firstOrNull()?.first?.also {
                    if (it < nextTime) {
                        action = CARRY_OLD
                        nextTime = it
                    }
                }

                putNew.firstOrNull()?.first?.also {
                    if (it < nextTime) {
                        action = PUT_NEW
                        nextTime = it
                    }
                }

                passBridge?.first?.also {
                    if (it < nextTime) {
                        action = PASS_BRIDGE
                        nextTime = it
                    }
                }

                when (action) {
                    NO_ACTION -> {
                        nextTime = currentTime
                    }

                    CARRY_OLD -> {
                        carryOld.poll()?.second?.also {
                            waitingRight.add(it)
                        }
                    }

                    PUT_NEW -> {
                        putNew.poll()?.second?.also {
                            waitingLeft.add(it)
                        }
                    }

                    PASS_BRIDGE -> {
                        passBridge?.also { (next, p) ->
                            val (staff, direction) = p

                            when (direction) {
                                LEFT_TO_RIGHT -> {
                                    carryOld.add(next + time[staff][1] to staff)

                                    if (debug) {
                                        println("${next} to ${next + time[staff][1]}: Staff ${staff} carry old")
                                    }
                                }

                                RIGHT_TO_LEFT -> {
                                    putNew.add(next + time[staff][3] to staff)

                                    if (debug) {
                                        println("${next} to ${next + time[staff][3]}: Staff ${staff} put new")
                                    }
                                }
                            }
                        }

                        passBridge = null
                    }
                }

                currentTime = nextTime

                pass()

                if (waitingRight.isEmpty() && carryOld.isEmpty() && passBridge == null && remainingBoxes == 0) {
                    break
                }
            }

            return currentTime
        }
    }

    measureTimeMillis {
        Solution().findCrossingTime(
            9, 6,
            arrayOf(
                intArrayOf(2, 6, 9, 4),
                intArrayOf(4, 8, 7, 5),
                intArrayOf(4, 6, 7, 6),
                intArrayOf(2, 3, 3, 7),
                intArrayOf(9, 3, 6, 8),
                intArrayOf(2, 8, 8, 4)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
