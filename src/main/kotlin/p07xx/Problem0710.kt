package p07xx

import java.util.*
import kotlin.random.Random
import util.expect

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
            return Random.nextInt(validCount).let { it + (blackMap.lowerEntry(it + 1)?.value ?: 0) }
        }
    }

    expect {
        val sol = Solution(4, intArrayOf(0, 1))
        sol.pick()
        sol.pick()
        sol.pick()
        sol.pick()
        sol.pick()
    }
}