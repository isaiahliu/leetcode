package p08xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxProfitAssignment(difficulty: IntArray, profit: IntArray, worker: IntArray): Int {
            var result = 0

            val map = TreeMap<Int, Int>()

            difficulty.forEachIndexed { index, diff ->
                val pro = profit[index]

                map.lowerEntry(diff + 1)?.value?.also {
                    if (it >= pro) {
                        return@forEachIndexed
                    }
                }

                while (true) {
                    map.higherEntry(diff)?.takeIf { it.value <= pro }?.key?.also {
                        map.remove(it)
                    } ?: break
                }

                map[diff] = pro
            }

            worker.forEach {
                result += map.lowerEntry(it + 1)?.value ?: 0
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().maxProfitAssignment(
            intArrayOf(), intArrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}