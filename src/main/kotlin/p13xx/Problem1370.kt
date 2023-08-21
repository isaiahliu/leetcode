package p13xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun sortString(s: String): String {
            val result = StringBuilder()

            val counts = TreeMap(s.groupingBy { it }.eachCount())

            var direction = false
            while (counts.isNotEmpty()) {
                var t: MutableMap.MutableEntry<Char, Int>? = if (direction) {
                    counts.lastEntry()
                } else {
                    counts.firstEntry()
                }

                while (t != null) {
                    result.append(t.key)

                    if (t.value == 1) {
                        counts.remove(t.key)
                    } else {
                        counts[t.key] = t.value - 1
                    }
                    t = if (direction) {
                        counts.lowerEntry(t.key)
                    } else {
                        counts.higherEntry(t.key)
                    }
                }

                direction = !direction
            }

            return result.toString()
        }
    }

    expect {
        Solution().sortString(
            ""
        )
    }
}

