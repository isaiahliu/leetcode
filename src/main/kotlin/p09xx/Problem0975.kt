package p09xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun oddEvenJumps(arr: IntArray): Int {
            val JUMP_UP = 1
            val JUMP_DOWN = 1 shl 1

            val map = TreeMap<Int, Int>()

            val dp = IntArray(arr.size)
            dp[dp.lastIndex] = JUMP_UP or JUMP_DOWN

            map[arr[arr.lastIndex]] = arr.lastIndex

            for (i in arr.lastIndex - 1 downTo 0) {
                val num = arr[i]

                var r = 0
                map[num]?.also {
                    if (dp[it] and JUMP_UP > 0) {
                        r += JUMP_DOWN
                    }

                    if (dp[it] and JUMP_DOWN > 0) {
                        r += JUMP_UP
                    }
                } ?: run {
                    map.higherEntry(num)?.value?.also {
                        if (dp[it] and JUMP_DOWN > 0) {
                            r += JUMP_UP
                        }
                    }

                    map.lowerEntry(num)?.value?.also {
                        if (dp[it] and JUMP_UP > 0) {
                            r += JUMP_DOWN
                        }
                    }
                }

                dp[i] = r
                map[num] = i
            }

            return dp.count { it and JUMP_UP > 0 }
        }
    }

    measureTimeMillis {
        Solution().oddEvenJumps(
            intArrayOf(5, 1, 3, 4, 2)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
