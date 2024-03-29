package p09xx

import util.expect

fun main() {
    class Solution {
        fun sumEvenAfterQueries(nums: IntArray, queries: Array<IntArray>): IntArray {
            val result = IntArray(queries.size)

            var sum = 0
            nums.forEach {
                if (it % 2 == 0) {
                    sum += it
                }
            }

            queries.forEachIndexed { index, (addValue, toIndex) ->
                if (nums[toIndex] % 2 == 0) {
                    sum -= nums[toIndex]
                }

                nums[toIndex] += addValue

                if (nums[toIndex] % 2 == 0) {
                    sum += nums[toIndex]
                }

                result[index] = sum
            }

            return result
        }
    }

    expect {
        Solution().sumEvenAfterQueries(
            intArrayOf(), arrayOf()
        )
    }
}
