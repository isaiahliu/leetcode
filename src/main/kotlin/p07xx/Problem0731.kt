package p07xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class MyCalendarTwo {
        val calendar = TreeMap<Int, Int>()

        fun book(start: Int, end: Int): Boolean {
            val left = calendar.lowerEntry(start + 1)

            if (left != null && left.value > 1) {
                return false
            }

            val sub = calendar.subMap(start, false, end, false)

            if (sub.any { it.value > 1 }) {
                return false
            }

            var last = (left?.value ?: 0) + 1
            calendar[start] = last
            sub.forEach { (key, value) ->
                last = value + 1
                calendar[key] = last
            }

            if (end !in calendar) {
                calendar[end] = last - 1
            }

            return true
        }
    }

    measureTimeMillis {
        val cal = MyCalendarTwo()
        cal.book(28, 46).also {
            println(cal.calendar)
            println(it)
        }
        cal.book(9, 21).also {
            println(cal.calendar)
            println(it)
        }
        cal.book(21, 39).also {
            println(cal.calendar)
            println(it)
        }

        cal.book(45, 50).also {
            println(cal.calendar)
            println(it)
        }
        cal.book(1, 12).also {
            println(cal.calendar)
            println(it)
        }
        cal.book(40, 50).also {
            println(cal.calendar)
            println(it)
        }
        cal.book(31, 44).also {
            println(cal.calendar)
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}