package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun repeatLimitedString(s: String, repeatLimit: Int): String {
            val map = TreeMap<Char, Int>()

            s.forEach {
                map[it] = (map[it] ?: 0) + 1
            }

            val result = StringBuilder()

            while (map.isNotEmpty()) {
                val (ch, count) = map.lastEntry()

                result.append(ch.toString().repeat(count.coerceAtMost(repeatLimit)))

                if (count > repeatLimit) {
                    map.lowerEntry(ch)?.also { (next, count) ->
                        result.append(next)

                        if (count == 1) {
                            map.remove(next)
                        } else {
                            map[next] = count - 1
                        }
                    } ?: break

                    map[ch] = count - repeatLimit
                } else {
                    map.remove(ch)
                }
            }

            return result.toString()
        }
    }

    expect {
        Solution().repeatLimitedString(
            "zzcccca", 3
        )
    }
}