package p16xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canDistribute(nums: IntArray, quantity: IntArray): Boolean {
            fun Int.forEachBit(consumer: (Int) -> Unit) {
                var t = this

                var index = 0
                while (t > 0) {
                    if (t % 2 == 1) {
                        consumer(index)
                    }

                    t /= 2
                    index++
                }
            }

            val counts = TreeMap<Int, Int>()

            nums.toList().groupingBy { it }.eachCount().values.forEach {
                counts[it] = (counts[it] ?: 0) + 1
            }

            quantity.sortDescending()

            var filterStatus = (1 shl quantity.size) - 1

            loop@ while (filterStatus > 0) {
                val sumMap = TreeMap<Int, Int>()

                for (status in 1 until (1 shl quantity.size)) {
                    if (status and filterStatus == status) {
                        var sum = 0

                        status.forEachBit {
                            sum += quantity[it]
                        }

                        if (sum in counts) {
                            counts[sum]?.also {
                                if (it == 1) {
                                    counts.remove(sum)
                                } else {
                                    counts[sum] = it - 1
                                }
                            }

                            filterStatus -= status
                            continue@loop
                        } else {
                            sumMap[sum] = sumMap[sum] ?: status
                        }
                    }
                }

                var minDiff = Int.MAX_VALUE
                var minDiffOrder = 0
                var minDiffStatus = 0
                counts.keys.forEach { order ->
                    sumMap.lowerEntry(order)?.takeIf { order - it.key < minDiff }?.also { (sum, status) ->
                        minDiffOrder = order
                        minDiff = order - sum
                        minDiffStatus = status
                    }
                }

                if (minDiff < Int.MAX_VALUE) {
                    counts[minDiffOrder]?.also {
                        if (it == 1) {
                            counts.remove(minDiffOrder)
                        } else {
                            counts[minDiffOrder] = it - 1
                        }

                        counts[minDiff] = (counts[minDiff] ?: 0) + 1
                    }

                    filterStatus -= minDiffStatus
                } else {
                    return false
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().canDistribute(
            intArrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

