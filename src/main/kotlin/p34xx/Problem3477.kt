package p34xx

import util.expect

fun main() {
    class Solution {
        fun numOfUnplacedFruits(fruits: IntArray, baskets: IntArray): Int {
            var result = 0

            fruits.forEach { f ->
                for (i in baskets.indices) {
                    if (f <= baskets[i]) {
                        baskets[i] = -1
                        return@forEach
                    }
                }

                result++
            }

            return result
        }
    }

    expect {
        Solution().numOfUnplacedFruits(
            intArrayOf(0, 17), intArrayOf(14, 19)
        )
    }
}
