package p17xx

import java.util.*
import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minAbsDifference(nums: IntArray, goal: Int): Int {
            if (goal == 0) {
                return 0
            }

            var result = goal.absoluteValue

            var posSum = 0
            var negSum = 0
            nums.forEachIndexed { index, num ->
                if (num > 0) {
                    posSum += num
                } else {
                    negSum += num
                }
            }

            var posGoal = if (goal > 0) {
                if (posSum <= goal) {
                    return goal - posSum
                } else {
                    posSum - goal
                }
            } else {
                if (negSum >= goal) {
                    return negSum - goal
                } else {
                    goal - negSum
                }
            }

            val totalSum = posSum - negSum
            posGoal = posGoal.coerceAtMost(totalSum - posGoal)

            result = result.coerceAtMost(posGoal)

            nums.forEachIndexed { index, num ->
                if (num < 0) {
                    nums[index] = -num
                }
            }

//            nums.sortDescending()

            fun toSumSet(startIndex: Int, endIndex: Int): TreeSet<Int> {
                val set = sortedSetOf(0)
                for (index in startIndex..endIndex) {
                    val num = nums[index]
                    when {
                        num > posGoal -> {
                            result = result.coerceAtMost(num - posGoal)
                        }

                        num > 0 -> {
                            result = result.coerceAtMost(posGoal - num)

                            set.toSet().forEach {
                                (num + it).also {
                                    if (it > posGoal) {
                                        result = result.coerceAtMost(it - posGoal)
                                    } else {
                                        result = result.coerceAtMost(posGoal - it)
                                        set.add(it)
                                    }
                                }

                                set.add(it)
                            }
                        }
                    }
                }

                return set
            }

            val leftSums = toSumSet(0, nums.lastIndex / 2)
            val rightSums = toSumSet(nums.lastIndex / 2 + 1, nums.lastIndex)

            if (result == 0) {
                return result
            }

            for (left in leftSums) {
                val target = posGoal - left

                rightSums.ceiling(target)?.also {
                    result = result.coerceAtMost(it - target)
                }

                rightSums.floor(target)?.also {
                    result = result.coerceAtMost(target - it)
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minAbsDifference(
            intArrayOf(
                -9014298,
                7566767,
                -914946,
                -2257280,
                8239178,
                -5556637,
                7324722,
                -6103364,
                1751734,
                4603195,
                -7295553,
                7969870,
                -9929798,
                6510645,
                2059081,
                -6955638,
                -878053,
                9735717,
                -2523152,
                6055974,
                -7750126,
                -3687910,
                -9507259,
                -5494812,
                5688988,
                -8798781,
                -2379329,
                -5893221,
                -4971163,
                1852649,
                3721357,
                -4412120
            ), 12592323
        ).also { println("${it} should be $it") }

        Solution().minAbsDifference(
            intArrayOf(
                1, 2, 3
            ), -7
        ).also { println("${it} should be $it") }

        Solution().minAbsDifference(
            intArrayOf(
                7, -9, 15, -2
            ), -5
        ).also { println("${it} should be $it") }

        Solution().minAbsDifference(
            intArrayOf(
                5, -7, 3, 5
            ), 6
        ).also { println("${it} should be $it") }

        Solution().minAbsDifference(
            intArrayOf(
                -7074297,
                3076735,
                -5846354,
                5008659,
                -126683,
                7039557,
                6708811,
                3189666,
                -6102417,
                6078975,
                -6448946,
                -4995910,
                2964239,
                -3248847,
                -4392269,
                7473223,
                -1356059,
                3978911,
                8009187,
                -316441,
                6524770,
                8280309,
                -2798383,
                1310839,
                6306594,
                -6548611,
                -9712711,
                1639314
            ), 493409180
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
