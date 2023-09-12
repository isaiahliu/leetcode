package p24xx

import util.expect

fun main() {
    class Solution {
        fun makeSimilar(nums: IntArray, target: IntArray): Long {
            val evenNums = nums.filter { it % 2 == 0 }.sorted()
            val evenTarget = target.filter { it % 2 == 0 }.sorted()

            val oddNums = nums.filter { it % 2 == 1 }.sorted()
            val oddTarget = target.filter { it % 2 == 1 }.sorted()

            var result = 0L

            evenNums.indices.forEach {
                result += (evenTarget[it] - evenNums[it]).takeIf { it > 0 } ?: 0
            }

            oddNums.indices.forEach {
                result += (oddTarget[it] - oddNums[it]).takeIf { it > 0 } ?: 0
            }

            return result / 2
        }
    }

    expect {
        Solution().makeSimilar(
            intArrayOf(8, 12, 6), intArrayOf(2, 14, 10)
        )
    }
}