package p16xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun bestTeamScore(scores: IntArray, ages: IntArray): Int {
            val dp = TreeMap<Int, Int>()
            scores.mapIndexed { index, score -> ages[index] to score }
                .sortedWith(compareBy<Pair<Int, Int>> { it.second }.thenBy { it.first }).forEach { (age, score) ->
                    val sum = (dp.lowerEntry(age + 1)?.value ?: 0) + score

                    dp[age] = sum

                    while (true) {
                        dp.higherEntry(age)?.takeIf { it.value <= sum }?.also {
                            dp.remove(it.key)
                        } ?: break
                    }
                }

            return dp.lastEntry().value
        }
    }

    expect {
        Solution().bestTeamScore(
            intArrayOf(4, 5, 6, 5), intArrayOf(2, 1, 2, 1)
        )
    }
}