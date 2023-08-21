package p04xx

import util.input
import util.expect

fun main() {
    class Solution {
        fun numberOfArithmeticSlices(nums: IntArray): Int {
            //key = targetNum
            //value = interval, size, count
            val targets = hashMapOf<Long, MutableMap<Pair<Long, Int>, Int>>()

            fun addTarget(target: Long, interval: Long, size: Int, count: Int) {
                val key = interval to size
                val targetMap = targets.computeIfAbsent(target) {
                    hashMapOf()
                }

                targetMap[key] = (targetMap[key] ?: 0) + count
            }

            for (i in nums.indices) {
                val num = nums[i].toLong()

                targets[num]?.entries?.sortedByDescending { it.key.second }?.onEach { (pair, count) ->
                    val (interval, size) = pair

                    val nextTarget = num + interval

                    addTarget(nextTarget, interval, size + 1, count)
                }

                for (j in 0 until i) {
                    val pre = nums[j].toLong()

                    val interval = num - pre

                    val target = num + interval

                    addTarget(target, interval, 2, 1)
                }
            }

            return targets.values.map {
                it.filter { it.key.second >= 3 }.values.sum()
            }.sum()
        }
    }


    expect {
//        Solution().numberOfArithmeticSlices(
//            intArrayOf(1, 2, 3, 4, 5, 6)
//        )

        Solution().numberOfArithmeticSlices(
            input.first().split(",").map { it.toInt() }.toIntArray()
        )
    }
}