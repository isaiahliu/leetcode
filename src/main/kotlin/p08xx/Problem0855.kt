package p08xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class ExamRoom(val n: Int) {
        val students = TreeSet<Int>()

        // index -- size
        val pos = TreeSet(compareByDescending<Pair<Int, Int>> { it.second }.thenBy { it.first })

        val posDistances = TreeMap<Int, Int>()

        init {
            pos.add(0 to n)
        }

        private fun add(index: Int, distance: Int) {
            pos.add(index to distance)
            posDistances[index] = distance
        }

        fun seat(): Int {
            val sitPos = pos.pollFirst()?.first ?: 0
            posDistances.remove(sitPos)

            val lower = students.lower(sitPos)
            val higher = students.higher(sitPos)
            students.add(sitPos)

            if (lower == null && sitPos > 0) {
                add(0, sitPos)
            }

            if (higher == null && sitPos < n - 1) {
                add(n - 1, n - 1 - sitPos)
            }

            if (lower != null && lower + 1 < sitPos) {
                add((sitPos + lower) / 2, (sitPos - lower) / 2)
            }

            if (higher != null && higher - 1 > sitPos) {
                add((higher + sitPos) / 2, (higher - sitPos) / 2)
            }

            return sitPos
        }

        fun leave(p: Int) {

            if (students.remove(p)) {
                val lower = students.lower(p)
                val higher = students.higher(p)

                when {
                    lower == null && higher == null -> {
                        pos.clear()
                        posDistances.clear()
                        add(0, n)
                    }

                    lower != null && higher == null -> {
                        if (lower + 1 < p) {
                            posDistances.lowerEntry(p)?.also { lowerSeat ->
                                pos.remove(lowerSeat.key to lowerSeat.value)
                                posDistances.remove(lowerSeat.key)
                            }
                        }

                        posDistances[n - 1]?.also {
                            pos.remove(n - 1 to it)
                        }

                        add(n - 1, n - 1 - lower)
                    }

                    lower == null && higher != null -> {
                        if (higher - 1 > p) {
                            posDistances.higherEntry(p)?.also { higherSeat ->
                                pos.remove(higherSeat.key to higherSeat.value)
                                posDistances.remove(higherSeat.key)
                            }
                        }
                        posDistances[0]?.also {
                            pos.remove(0 to it)
                        }

                        add(0, higher)
                    }

                    else -> {
                        val subSeats = posDistances.subMap(lower, false, higher, false)

                        subSeats.forEach {
                            pos.remove(it.key to it.value)
                        }

                        subSeats.clear()

                        add((higher + lower) / 2, (higher - lower) / 2)
                    }
                }
            }
        }
    }

    measureTimeMillis {
        val room = ExamRoom(4)
        room.seat().also { println(it) }
        room.seat().also { println(it) }
        room.seat().also { println(it) }
        room.seat().also { println(it) }

        room.leave(1)
        room.leave(3)

        room.seat().also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}