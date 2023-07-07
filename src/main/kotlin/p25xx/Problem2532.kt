package p25xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findCrossingTime(n: Int, k: Int, time: Array<IntArray>): Int {
            val waitingLeft =
                PriorityQueue(compareByDescending<Int> { time[it][0] + time[it][2] }.thenByDescending { it })
            val pickOld = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
            val putNew = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
            var passBridge: Pair<Int, Pair<Int, Boolean>>? = null
            val waitingRight =
                PriorityQueue(compareByDescending<Int> { time[it][0] + time[it][2] }.thenByDescending { it })

            var remainingBoxes = n

            repeat(k) {
                waitingLeft.add(it)
            }

            var result = 0

            val NO_ACTION = 0
            val PASS_BRIDGE = 1
            val PICK_OLD = 2
            val PUT_NEW = 3

            val debug = false

            fun pass() {
                when {
                    passBridge != null -> {

                    }

                    waitingRight.isNotEmpty() -> {
                        val staff = waitingRight.poll()

                        passBridge = (result + time[staff][2]) to (staff to false)

                        if (debug) {
                            println("$result to ${result + time[staff][2]}: Staff $staff moving right to left")
                        }
                    }

                    waitingLeft.isNotEmpty() && remainingBoxes > 0 -> {
                        val staff = waitingLeft.poll()

                        passBridge = (result + time[staff][0]) to (staff to true)

                        remainingBoxes--

                        if (debug) {
                            println("$result to ${result + time[staff][0]}: Staff $staff moving left to right")
                        }
                    }
                }
            }

            loop@ while (true) {
                var nextAction = NO_ACTION
                var nextTime = Int.MAX_VALUE

                pickOld.peek()?.first?.also {
                    if (it < nextTime) {
                        nextAction = PICK_OLD
                        nextTime = it
                    }
                }

                putNew.peek()?.first?.also {
                    if (it < nextTime) {
                        nextAction = PUT_NEW
                        nextTime = it
                    }
                }

                passBridge?.first?.also {
                    if (it < nextTime) {
                        nextAction = PASS_BRIDGE
                        nextTime = it
                    }
                }

                when (nextAction) {
                    NO_ACTION -> {
                        nextTime = result
                    }

                    PICK_OLD -> {
                        pickOld.poll()?.second?.also {
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

                            if (direction) {
                                pickOld.add(next + time[staff][1] to staff)

                                if (debug) {
                                    println("$next to ${next + time[staff][1]}: Staff $staff pick old")
                                }
                            } else {
                                putNew.add(next + time[staff][3] to staff)

                                if (debug) {
                                    println("$next to ${next + time[staff][3]}: Staff $staff put new")
                                }
                            }
                        }

                        passBridge = null
                    }
                }

                result = nextTime

                pass()

                if (waitingLeft.size + putNew.size == k && remainingBoxes == 0) {
                    break
                }
            }

            return result
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
