package p23xx

import util.expect

fun main() {
    class Solution {
        fun minimumScore(nums: IntArray, edges: Array<IntArray>): Int {
            val adjacent = Array(nums.size) { hashSetOf<Int>() }
            val times = Array(nums.size) { intArrayOf(Int.MAX_VALUE, Int.MIN_VALUE) }
            val sums = IntArray(nums.size) { -1 }

            edges.forEach { (from, to) ->
                adjacent[from].add(to)
                adjacent[to].add(from)
            }

            var timestamp = 0
            fun dfs(index: Int) {
                var sum = nums[index]

                times[index][0] = timestamp++

                adjacent[index].forEach { child ->
                    if (times[child][0] > timestamp) {
                        dfs(child)

                        sum = sum xor sums[child]
                    }
                }

                times[index][1] = timestamp++
                sums[index] = sum
            }

            dfs(0)

            var result = Int.MAX_VALUE
            val rootSum = nums.fold(0) { a, b -> a xor b }
            for (edgeIndex1 in edges.indices) {
                val (f1, t1) = edges[edgeIndex1]
                val subRoot1 = if (times[f1][0] > times[t1][0]) f1 else t1
                val (from1, to1) = times[subRoot1]

                for (edgeIndex2 in edgeIndex1 + 1 until edges.size) {
                    val (f2, t2) = edges[edgeIndex2]
                    val subRoot2 = if (times[f2][0] > times[t2][0]) f2 else t2
                    val (from2, to2) = times[subRoot2]

                    when {
                        from1 > to2 || from2 > to1 -> {
                            val s1 = sums[subRoot1]
                            val s2 = sums[subRoot2]
                            val r = rootSum xor s1 xor s2

                            result = result.coerceAtMost(maxOf(s1, s2, r) - minOf(s1, s2, r))
                        }

                        from1 > from2 -> {
                            val s1 = sums[subRoot1]
                            val s2 = sums[subRoot2] xor s1
                            val r = rootSum xor sums[subRoot2]

                            result = result.coerceAtMost(maxOf(s1, s2, r) - minOf(s1, s2, r))
                        }

                        else -> {
                            val s2 = sums[subRoot2]
                            val s1 = sums[subRoot1] xor s2
                            val r = rootSum xor sums[subRoot1]

                            result = result.coerceAtMost(maxOf(s1, s2, r) - minOf(s1, s2, r))
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumScore(
            intArrayOf(1, 5, 5, 4, 11), arrayOf(
                intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(1, 3), intArrayOf(3, 4)
            )
        )
    }
}