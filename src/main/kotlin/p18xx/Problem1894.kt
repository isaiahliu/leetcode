package p18xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun chalkReplacer(chalk: IntArray, k: Int): Int {
            var sum = 0L
            val treeMap = TreeMap<Long, Int>()

            chalk.forEachIndexed { index, num ->
                sum += num
                treeMap[sum] = index
            }

            return treeMap.higherEntry(k.toLong() % sum).value
        }
    }

    measureTimeMillis {
        Solution().chalkReplacer(
            intArrayOf(), 1
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
