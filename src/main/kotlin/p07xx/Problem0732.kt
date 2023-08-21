package p07xx

import java.util.*
import util.expect

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

    expect {
        val cal = MyCalendarThree()
        cal.book(10, 20).also {
            cal.calendar
            it
        }
        cal.book(50, 60).also {
            cal.calendar
            it
        }
        cal.book(10, 40).also {
            cal.calendar
            it
        }
        cal.book(5, 15).also {
            cal.calendar
            it
        }
        cal.book(5, 10).also {
            cal.calendar
            it
        }
        cal.book(25, 55).also {
            cal.calendar
            it
        }

    }
}