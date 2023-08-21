package p07xx

import java.util.*
import util.expect

fun main() {
    class RangeModule {
        val map = TreeMap<Int, Int>()

        fun addRange(left: Int, right: Int) {
            var l = left
            var r = right
            map.lowerEntry(left + 1)?.takeIf { it.value >= l }?.also {
                if (it.value >= right) {
                    return
                } else {
                    l = it.key
                }
            }

            while (true) {
                val next = map.higherEntry(left)?.takeIf { it.key <= right + 1 } ?: break

                map.remove(next.key)
                r = r.coerceAtLeast(next.value)
            }

            map[l] = r
        }

        fun queryRange(left: Int, right: Int): Boolean {
            return map.lowerEntry(left + 1)?.value?.takeIf {
                it >= right
            } != null
        }

        fun removeRange(left: Int, right: Int) {
            map.lowerEntry(left)?.takeIf { it.value > left }?.also {
                map[it.key] = left
                map[left] = it.value
            }

            while (true) {
                map.higherEntry(left - 1)?.takeIf { it.key < right }?.also { (key, value) ->
                    map.remove(key)
                    if (value > right) {
                        map[right] = value
                    }
                } ?: break
            }
        }
    }

    expect {
        val sol = RangeModule()
        sol.addRange(6, 8)
        sol.removeRange(7, 8)
        sol.removeRange(8, 9)
        sol.addRange(8, 9)
        sol.removeRange(1, 3)
        sol.addRange(1, 8)

        sol.queryRange(2, 4)
        sol.queryRange(2, 9)
        sol.queryRange(4, 6)
    }
}