package p00xx

import util.expect

fun main() {
    class Solution {
        fun subsetsWithDup(nums: IntArray): List<List<Int>> {
            var result = listOf(emptyList<Int>())

            nums.toList().groupingBy { it }.eachCount().forEach { (num, count) ->
                result = (0..count).map {
                    val appendList = arrayListOf<Int>()
                    repeat(it) {
                        appendList.add(num)
                    }

                    result.map {
                        it + appendList
                    }
                }.flatten()
            }

            return result
        }
    }

    expect {
        Solution().subsetsWithDup(
            intArrayOf(4)
        )
    }
}

