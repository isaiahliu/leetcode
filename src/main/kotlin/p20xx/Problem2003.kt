package p20xx

import util.expect

fun main() {
    class Solution {
        fun smallestMissingValueSubtree(parents: IntArray, nums: IntArray): IntArray {
            var oneIndex = -1
            val result = IntArray(parents.size) {
                if (nums[it] == 1) {
                    oneIndex = it
                }
                1
            }

            if (oneIndex >= 0) {
                val map = hashMapOf<Int, Int>()

                val list = arrayListOf<Int>()

                var t = oneIndex
                while (t >= 0) {
                    map[nums[t]] = list.size

                    list.add(t)
                    t = parents[t]
                }

                nums.forEachIndexed { index, num ->
                    val listIndex = map[num] ?: run {
                        var i = parents[index]
                        while (nums[i] !in map) {
                            i = parents[i]
                        }

                        map[nums[i]]
                    } ?: 0

                    map[num] = listIndex
                }

                var leftIndex = 0
                var current = 2

                while (true) {
                    val rightIndex = map[current]

                    if (rightIndex == null) {
                        for (i in leftIndex until list.size) {
                            result[list[i]] = current
                        }

                        break
                    } else if (rightIndex > leftIndex) {
                        for (i in leftIndex until rightIndex) {
                            result[list[i]] = current
                        }

                        leftIndex = rightIndex
                    }

                    current++
                }
            }

            return result
        }
    }

    expect {
        Solution().smallestMissingValueSubtree(
            intArrayOf(-1, 0, 0, 0, 2), intArrayOf(6, 4, 3, 2, 1)
        ).toList()
    }
}