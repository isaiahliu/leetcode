package p08xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun shortestToChar(s: String, c: Char): IntArray {
            val set = TreeSet<Int>()

            s.forEachIndexed { index, ch ->
                if (ch == c) {
                    set.add(index)
                }
            }

            return s.mapIndexed { index, ch ->
                if (ch == c) {
                    0
                } else {
                    var r = s.length

                    set.lower(index)?.also {
                        r = r.coerceAtMost(index - it)
                    }

                    set.higher(index)?.also {
                        r = r.coerceAtMost(it - index)
                    }

                    r
                }
            }.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().shortestToChar(
            "", '1'
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}