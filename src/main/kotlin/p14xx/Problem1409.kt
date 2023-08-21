package p14xx

import util.expect

fun main() {
    class Solution {
        fun processQueries(queries: IntArray, m: Int): IntArray {
            val nums = MutableList(m) { it + 1 }

            return queries.map {
                var index = 0

                while (nums[index] != it) {
                    index++
                }

                nums.removeAt(index)
                nums.add(0, it)

                index
            }.toIntArray()
        }
    }

    expect {
        Solution().processQueries(
            intArrayOf(), 5
        )
    }
}

