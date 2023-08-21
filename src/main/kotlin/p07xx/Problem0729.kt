package p07xx

import java.util.*
import util.expect

fun main() {
    class MyCalendar {
        val map = TreeMap<Int, Int>()

        fun book(start: Int, end: Int): Boolean {
            var left = start
            var right = end

            map.lowerEntry(left + 1)?.also {
                when {
                    it.value > start -> return false
                    it.value == start -> left = it.key
                }
            }

            map.higherEntry(left)?.also {
                when {
                    end > it.key -> return false
                    end == it.key -> {
                        right = it.value
                        map.remove(it.key)
                    }
                }
            }

            map[left] = right

            return true
        }
    }

    expect {
        MyCalendar().book(
            1, 2
        )
    }
}