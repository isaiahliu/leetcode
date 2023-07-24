package p13xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numTeams(rating: IntArray): Int {
            rating.indices.sortedBy { rating[it] }.forEachIndexed { seq, index -> rating[index] = seq }

            val set = TreeSet<Int>()

            var result = 0

            rating.forEach {
                val lessSize = set.headSet(it).size
                val greaterSize = set.tailSet(it).size

                val rightGreaterCount = rating.size - it - 1 - greaterSize

                val rightLessCount = it - lessSize

                result += lessSize * rightGreaterCount + greaterSize * rightLessCount
                set.add(it)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().numTeams(
            intArrayOf(3, 7, 5, 6),
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

