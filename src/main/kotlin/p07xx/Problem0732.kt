package p07xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class MyCalendarThree {
        val calendar = TreeMap<Int, Int>()
        var max = 0

        fun book(startTime: Int, endTime: Int): Int {
            val left = calendar.lowerEntry(startTime + 1)

            val sub = calendar.subMap(startTime, false, endTime, false)

            var last = (left?.value ?: 0) + 1
            max = max.coerceAtLeast(last)
            calendar[startTime] = last
            sub.forEach { (key, value) ->
                last = value + 1
                max = max.coerceAtLeast(last)
                calendar[key] = last
            }

            if (endTime !in calendar) {
                calendar[endTime] = last - 1
            }

            return max
        }
    }

    measureTimeMillis {
        val cal = MyCalendarThree()
        cal.book(10, 20).also {
            println(cal.calendar)
            println(it)
        }
        cal.book(50, 60).also {
            println(cal.calendar)
            println(it)
        }
        cal.book(10, 40).also {
            println(cal.calendar)
            println(it)
        }
        cal.book(5, 15).also {
            println(cal.calendar)
            println(it)
        }
        cal.book(5, 10).also {
            println(cal.calendar)
            println(it)
        }
        cal.book(25, 55).also {
            println(cal.calendar)
            println(it)
        }

    }.also { println("Time cost: ${it}ms") }
}