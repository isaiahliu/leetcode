package p07xx

import java.util.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    class Solution(n: Int, blacklist: IntArray) {
        val blackMap = TreeMap<Int, Int>()

        val validCount = n - blacklist.size

        init {
            var skipCount = 0

            blacklist.sorted().forEach {
                blackMap[it - skipCount] = ++skipCount
            }
        }

        fun pick(): Int {
            val num = Random.nextInt(validCount)

            return num + (blackMap.lowerEntry(num + 1)?.value ?: 0)
        }
    }

    measureTimeMillis {
        val sol = Solution(4, intArrayOf(0, 1))
        sol.pick().also { println(it) }
        sol.pick().also { println(it) }
        sol.pick().also { println(it) }
        sol.pick().also { println(it) }
        sol.pick().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}