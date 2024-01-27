package p28xx

import util.expect

fun main() {
    class Solution {
        fun maxNumberOfAlloys(
            n: Int, k: Int, budget: Int, composition: List<List<Int>>, stock: List<Int>, cost: List<Int>
        ): Int {
            return composition.indices.maxOf {
                val comp = composition[it]

                var min = 0L
                var max = Int.MAX_VALUE.toLong()
                var result = 0
                while (min <= max) {
                    val mid = (min + max) / 2

                    val total = comp.indices.sumOf {
                        maxOf(comp[it] * mid - stock[it], 0L) * cost[it]
                    }

                    if (total <= budget) {
                        result = mid.toInt()

                        min = mid + 1
                    } else {
                        max = mid - 1
                    }
                }

                result
            }
        }
    }

    expect {
        Solution().maxNumberOfAlloys(
            1, 2, 3, listOf(), listOf(), listOf()
        )
    }
}
