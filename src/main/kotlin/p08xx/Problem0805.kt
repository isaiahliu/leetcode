package p08xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun splitArraySameAverage(nums: IntArray): Boolean {
            val size = nums.size
            if (size < 2) {
                return false
            }

            return when (val sum = nums.sum()) {
                0 -> {
                    true
                }

                1 -> {
                    false
                }

                else -> {
                    if (nums.any { it * nums.size == sum }) {
                        return true
                    }

                    val posMap = TreeMap<Int, MutableSet<Int>>(compareByDescending { it })
                    val negMap = TreeMap<Int, MutableSet<Int>>(compareByDescending { it })
                    posMap[0] = mutableSetOf(0)
                    negMap[0] = mutableSetOf(0)
                    nums.take(nums.size / 2).forEach { num ->
                        posMap.toMap().forEach { (c, s) ->
                            val set = posMap.computeIfAbsent(c + 1) { hashSetOf() }
                            s.forEach {
                                set.add(it + num)
                            }
                        }
                    }

                    nums.drop(nums.size / 2).forEach { num ->
                        negMap.toMap().forEach { (c, s) ->
                            val set = negMap.computeIfAbsent(c + 1) { hashSetOf() }
                            s.forEach {
                                set.add(it + num)

                                posMap.forEach { (posC, posSums) ->
                                    val subCount = c + 1 + posC
                                    if (subCount < nums.size && subCount * sum % nums.size == 0) {
                                        if (subCount * sum / nums.size - it - num in posSums) {
                                            return true
                                        }
                                    }
                                }
                            }
                        }
                    }

                    false
                }
            }
        }
    }

    expect {
        Solution().splitArraySameAverage(
            intArrayOf(
                1 shl 0,
                1 shl 1,
                1 shl 2,
                1 shl 3,
                1 shl 4,
                1 shl 5,
                1 shl 6,
                1 shl 7,
                1 shl 8,
                1 shl 9,
                1 shl 10,
                1 shl 11,
                1 shl 12,
                1 shl 13,
                1 shl 14,
                1 shl 15,
                1 shl 16,
                1 shl 17,
                1 shl 18,
                1 shl 19,
                1 shl 20,
                1 shl 21,
                1 shl 22,
                1 shl 23,
                1 shl 24,
                1 shl 25,
                1 shl 26,
                1 shl 27,
                1 shl 28,
                1 shl 29,
                1 shl 30
            )
        )
    }
}