package p12xx

import util.expect

fun main() {
    class Solution {
        fun groupThePeople(groupSizes: IntArray): List<List<Int>> {
            val result = arrayListOf<List<Int>>()
            val groups = hashMapOf<Int, MutableList<Int>>()
            groupSizes.forEachIndexed { index, size ->
                val list = groups.computeIfAbsent(size) { arrayListOf() }

                list.add(index)

                if (list.size == size) {
                    result.add(list)

                    groups.remove(size)
                }
            }

            return if (groups.isEmpty()) {
                result
            } else {
                emptyList()
            }
        }
    }

    expect {
        Solution().groupThePeople(
            intArrayOf()
        )
    }
}
