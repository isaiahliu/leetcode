package p17xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxValue(events: Array<IntArray>, k: Int): Int {
            events.sortBy { it[0] }

            var dp = TreeMap<Int, Int>()
            dp[-1] = 0

            var result = 0

            for (idx in 0 until k) {
                val newDp = TreeMap<Int, Int>()

                events.forEach { (start, end, value) ->
                    dp.lowerEntry(start)?.value?.also {
                        val bestValue = value + it

                        if (newDp.floorEntry(end)?.takeIf { it.value >= bestValue } == null) {
                            while (true) {
                                newDp.higherEntry(end)?.takeIf { it.value <= bestValue }?.also {
                                    newDp.remove(it.key)
                                } ?: break
                            }

                            newDp[end] = bestValue
                        }
                    }
                }

                dp = newDp

                dp.lastEntry()?.value?.also {
                    result = result.coerceAtLeast(it)
                } ?: break
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxValue(
            arrayOf(), 2
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
