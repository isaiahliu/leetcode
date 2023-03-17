package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
            fun binarySearch(leftIndex: Int, rightIndex: Int, target: Int): Int {
                if (leftIndex > rightIndex) {
                    return -1
                }

                if (target > candidates[rightIndex] || target < candidates[leftIndex]) {
                    return -1
                }

                if (leftIndex == rightIndex) {
                    return if (candidates[leftIndex] == target) leftIndex else -1
                }

                val midIndex = leftIndex + (rightIndex - leftIndex) / 2

                return if (candidates[midIndex] == target) {
                    midIndex
                } else if (candidates[midIndex] > target) {
                    binarySearch(leftIndex, midIndex, target)
                } else {
                    binarySearch(midIndex + 1, rightIndex, target)
                }
            }

            candidates.sort()

            val result = arrayListOf<List<Int>>()

            if (binarySearch(0, candidates.lastIndex, target) >= 0) {
                result.add(listOf(target))
            }

            fun walk(minIndex: Int, route: List<Int>, target: Int) {
                for (i in minIndex until candidates.size) {
                    val num = candidates[i]

                    val remaining = target - num

                    if (remaining == num) {
                        result.add(route + num + num)
                        break
                    } else if (remaining < num) {
                        break
                    } else {
                        if (binarySearch(i + 1, candidates.lastIndex, remaining) >= 0) {
                            result.add(route + num + remaining)
                        }

                        walk(i, route + num, remaining)
                    }
                }
            }

            walk(0, emptyList(), target)

            return result
        }
    }

    measureTimeMillis {
        println(Solution().combinationSum(intArrayOf(3, 5), 8))
    }.also { println("Time cost: ${it}ms") }
}


