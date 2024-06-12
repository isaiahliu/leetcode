package p28xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun findMaximumElegance(items: Array<IntArray>, k: Int): Long {
            items.sortByDescending { it[0] }

            var size = 0
            var result = 0L

            var profitSum = 0L
            val visitedCategories = hashSetOf<Int>()
            val categoryCounts = hashMapOf<Int, Int>()

            val queue = LinkedList<Pair<Int, Int>>()
            for ((profit, category) in items) {
                when {
                    size < k -> {
                        size++
                        profitSum += profit
                        categoryCounts[category] = (categoryCounts[category] ?: 0) + 1
                        visitedCategories.add(category)
                        queue.push(profit to category)
                        if (size == k) {
                            result = profitSum + 1L * categoryCounts.size * categoryCounts.size
                        }
                    }

                    queue.isEmpty() -> {
                        break
                    }

                    visitedCategories.add(category) -> {
                        while (queue.isNotEmpty()) {
                            val (lastP, lastC) = queue.poll()

                            val count = categoryCounts[lastC] ?: 0
                            if (count > 1) {
                                categoryCounts[lastC] = count - 1

                                profitSum -= lastP
                                profitSum += profit

                                result = maxOf(
                                    result,
                                    profitSum + 1L * visitedCategories.size * visitedCategories.size
                                )
                                break
                            }
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().findMaximumElegance(
            arrayOf(
                intArrayOf(3, 2),
                intArrayOf(5, 1),
                intArrayOf(10, 1),
            ), 2
        )
    }
}
