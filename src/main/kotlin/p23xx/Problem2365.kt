package p23xx

import util.expect

fun main() {
    class Solution {
        fun taskSchedulerII(tasks: IntArray, space: Int): Long {
            val lastTime = hashMapOf<Int, Int>()
            var result = tasks.size.toLong()

            var today = 0
            tasks.forEach { job ->
                lastTime[job]?.also {
                    (space - (today - it - 1)).takeIf { it > 0 }?.also {
                        today += it
                        result += it
                    }
                }

                lastTime[job] = today++
            }

            return result
        }
    }

    expect {
        Solution().taskSchedulerII(
            intArrayOf(1, 2, 1, 2, 3, 1), 3
        )
    }
}