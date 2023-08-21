package p16xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun minimumIncompatibility(nums: IntArray, k: Int): Int {
            if (k == nums.size) {
                return 0
            }
            nums.sort()

            val init = arrayListOf<Pair<Int, Int>>()

            val min = nums[0]
            var count = 0
            var pre = 0
            nums.forEach {
                (it - min).also {
                    if (it == pre) {
                        count++
                    } else {
                        init.add(pre to count)
                        pre = it
                        count = 1
                    }
                }
            }

            init.add(pre to count)

            if (k == 1) {
                return if (init.size == nums.size) {
                    nums[nums.lastIndex] - nums[0]
                } else {
                    -1
                }

            }

            val cache = hashMapOf<String, Int?>()

            val groupSize = nums.size / k

            fun dfs(numArr: List<Pair<Int, Int>>, remainingGroups: Int): Int? {
                if (remainingGroups == 0) {
                    return 0
                }

                val cacheKey = numArr.joinToString(";") {
                    "${it.first},${it.second}"
                }

                if (cacheKey in cache) {
                    return cache[cacheKey]
                }

                var result: Int = Int.MAX_VALUE

                if (numArr.none { it.second > remainingGroups }) {
                    val selectedIndices = LinkedList<Int>()

                    repeat(groupSize - 1) {
                        selectedIndices.add(it + 1)
                    }

                    loop@ while (true) {
                        val next = arrayListOf<Pair<Int, Int>>()

                        var m = Int.MAX_VALUE
                        numArr.forEachIndexed { index, (num, count) ->
                            var c = count
                            if (index == 0 || index in selectedIndices) {
                                c--
                            }

                            if (c > 0) {
                                m = m.coerceAtMost(num)

                                next.add(num - m to c)
                            }
                        }

                        dfs(next, remainingGroups - 1)?.also {
                            result = result.coerceAtMost(it + numArr[selectedIndices.peekLast()].first)
                        }

                        while (true) {
                            var lastIndex = selectedIndices.pollLast() ?: break@loop

                            if (groupSize - selectedIndices.size + lastIndex <= numArr.size) {
                                while (selectedIndices.size < groupSize - 1) {
                                    selectedIndices.add(++lastIndex)
                                }

                                break
                            }
                        }
                    }
                }

                return result.takeIf { it < Int.MAX_VALUE }.also {
                    cache[cacheKey] = it
                }
            }

            return dfs(init, k) ?: -1
        }
    }

    expect {
        Solution().minimumIncompatibility(
            intArrayOf(4, 10, 4, 7, 9, 10, 4, 6, 9, 10), 5
        )
    }
}