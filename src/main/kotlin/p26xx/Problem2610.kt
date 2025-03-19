package p26xx

import util.expect

fun main() {
    class Solution {
        fun findMatrix(nums: IntArray): List<List<Int>> {
            val list = arrayListOf<MutableSet<Int>>()

            nums.forEach { num ->
                for (set in list) {
                    if (set.add(num)) {
                        return@forEach
                    }
                }

                list.add(hashSetOf(num))
            }

            return list.map { it.toList() }
        }
    }

    expect {
        Solution().findMatrix(
            intArrayOf()
        )
    }
}
